using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;


public class api : MonoBehaviour
{
    [System.Serializable]      
    public class MyMission
    {
        public int id;
        public float x;
        public float y;
        public float theta;
    }
    public MyMission mission = new MyMission();
    public float speed = 2;
    public Vector3 targetPosition;
    public float rotationAngle;
    public float moveSpeed = 5f;
    public float rotationSpeed = 100f;
    public float arrivalThreshold = 0.1f; // Adjust this threshold based on your needs
    private bool destinationReached = false;


    private const string URL = "http://localhost:8085/mission/mission";

    public void GenerateRequest()
    {
        StartCoroutine(ProcessRequest(URL));
    }

    private IEnumerator ProcessRequest(string uri)
    {
        //UnityWebRequest.isNetworkError is deprecated. Use (UnityWebRequest.result == UnityWebRequest.Result.ConnectionError) instead.'
        using (UnityWebRequest request = UnityWebRequest.Get(uri))
        {
            yield return request.SendWebRequest();

            if (request.isNetworkError)
            {
                Debug.Log(request.error);
            }
            else
            {
                Debug.Log(request.downloadHandler.text);
                mission = JsonUtility.FromJson<MyMission>(request.downloadHandler.text);
                //Debug.Log("id:" + mission.id.ToString());                 
                //Debug.Log("x:" + mission.x.ToString());                 
                //Debug.Log("y:" + mission.y.ToString());                 
                //Debug.Log("theta:" + mission.theta.ToString());                 
            }
        }
    }

    // Start is called before the first frame update
    void Start()
    {
        GenerateRequest();
        UpdateTarget(new Vector3(mission.x, 0f, mission.y), mission.theta);  
    }


    // Update is called once per frame
    void Update()
    {
        //MoveToTargetPosition();
        //RotateToTargetAngle();  
        CheckReachedDestination();
        if (!destinationReached)
        {
            //MoveAndRotateTowardsTarget();
            MoveToTargetPosition();
            RotateToTargetAngle();  
        } 
        else 
        {
            GenerateRequest();
            UpdateTarget(new Vector3(mission.x, 0f, mission.y), mission.theta);  
        }
    }


// Method to initialize the target position and rotation angle
    public void UpdateTarget(Vector3 newPosition, float newRotationAngle)
    {
        targetPosition = newPosition;
        rotationAngle = newRotationAngle;
        destinationReached = false; // Reset the flag when a new destination is set

    }    
    void MoveToTargetPosition()
    {
        float step = moveSpeed * Time.deltaTime;
        transform.position = Vector3.MoveTowards(transform.position, targetPosition, step);
    }

    void RotateToTargetAngle()
    {
        float step = rotationSpeed * Time.deltaTime;
        Quaternion targetRotation = Quaternion.Euler(0f, rotationAngle, 0f);
        transform.rotation = Quaternion.RotateTowards(transform.rotation, targetRotation, step);
    }
    void MoveAndRotateTowardsTarget()
    {
        float step = moveSpeed * Time.deltaTime;
        // Move towards the target position
        transform.Translate(Vector3.forward * step);
        // Calculate the rotation towards the target position
        Quaternion targetRotation = Quaternion.LookRotation(targetPosition - transform.position);
        // Smoothly rotate towards the target rotation
        transform.rotation = Quaternion.RotateTowards(transform.rotation, targetRotation, rotationSpeed * Time.deltaTime);
    }

    void CheckReachedDestination()
    {
        if (Vector3.Distance(transform.position, targetPosition) < arrivalThreshold)
        {
            Debug.Log("Destination reached!");
            // Optionally, perform additional actions when the destination is reached
            destinationReached = true;
        }
    }        
}
