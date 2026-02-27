#include <iostream>
#include <string>
#include <chrono>
#include <thread>
#include <curl/curl.h>

struct Robot {
    int id;
    double x, y, theta, v, ultrasound;
};

struct Mission {
    int id;
    double x, y, theta;
};

size_t writeCallback(void* contents, size_t size, size_t nmemb, std::string* output) {
    size_t total_size = size * nmemb;
    output->append((char*)contents, total_size);
    return total_size;
}
void updateRobot(const Robot& robot) {
    CURL* curl;
    CURLcode res;
    std::string response;

    curl = curl_easy_init();

    if (curl) {
        // URL de l'endpoint
        std::string url = "http://localhost:8085/robot/" + std::to_string(robot.id);

        // Corps JSON de la requête
        std::string jsonBody = "{"
            "\"id\":" + std::to_string(robot.id) + ","
            "\"x\":"  + std::to_string(robot.x)  + ","
            "\"y\":"  + std::to_string(robot.y)  + ","
            "\"theta\":"     + std::to_string(robot.theta)     + ","
            "\"v\":"         + std::to_string(robot.v)         + ","
            "\"ultraSound\":" + std::to_string(robot.ultrasound) +
        "}";

        // Headers HTTP
        struct curl_slist* headers = nullptr;
        headers = curl_slist_append(headers, "Content-Type: application/json");

        // Configuration de la requête PUT
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
        curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, "PUT");
        curl_easy_setopt(curl, CURLOPT_POSTFIELDS, jsonBody.c_str());
        curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writeCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

        // Envoi de la requête
        res = curl_easy_perform(curl);

        if (res != CURLE_OK) {
            std::cerr << "PUT failed: " << curl_easy_strerror(res) << std::endl;
        } else {
            std::cout << "Robot mis à jour : " << response << std::endl;
        }

        curl_slist_free_all(headers);
        curl_easy_cleanup(curl);
    }
}

Mission getMission() {
    CURL* curl;
    CURLcode res;
    std::string response;
    Mission mission = {0, 0.0, 0.0, 0.0};

    curl = curl_easy_init();

    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8085/mission/mission");
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, writeCallback);
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

        res = curl_easy_perform(curl);

        if (res != CURLE_OK) {
            std::cerr << "GET failed: " << curl_easy_strerror(res) << std::endl;
        } else {
            std::cout << "Mission reçue : " << response << std::endl;

            // Parse manuel du JSON : {"id":1,"x":0.0,"y":0.0,"theta":0.0}
            auto extract = [&](const std::string& key) -> double {
                std::string search = "\"" + key + "\":";
                size_t pos = response.find(search);
                if (pos == std::string::npos) return 0.0;
                pos += search.length();
                return std::stod(response.substr(pos));
            };

            mission.id    = (int)extract("id");
            mission.x     = extract("x");
            mission.y     = extract("y");
            mission.theta = extract("theta");
        }

        curl_easy_cleanup(curl);
    }

    return mission;
}

int main() {
    Robot myRobot = {1, 0.0, 0.0, 0.0, 1.0, 2.0};

    std::cout << "=== Démarrage du robot ===" << std::endl;

    // Créer le robot dans la base au démarrage
    // (optionnel si déjà créé via Postman)

    while (true) {
        // 1. Récupérer la prochaine mission
        Mission mission = getMission();

        // 2. Mettre à jour la position du robot avec la mission reçue
        myRobot.x     = mission.x;
        myRobot.y     = mission.y;
        myRobot.theta = mission.theta;

        // 3. Envoyer la mise à jour au serveur
        updateRobot(myRobot);

        // 4. Attendre 3 secondes avant la prochaine mise à jour
        std::cout << "Attente 3 secondes..." << std::endl;
        std::this_thread::sleep_for(std::chrono::seconds(3));
    }

    return 0;
}