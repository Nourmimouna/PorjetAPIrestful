package com.eidd.mission.service;

import com.eidd.mission.db.entity.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/mission") // This means URL's start with /mission
public class MissionController {

    @Autowired
    @Qualifier("mission_service")
    private MissionService missionService;


    @GetMapping(path = "/all")
    public @ResponseBody List<Mission> getMissions() {
        // This returns a JSON or XML with the users
        return missionService.getAll();
    }

    @GetMapping(path = "/mission")
    public @ResponseBody Mission getMission() {
        // This returns a JSON or XML with the users
        return missionService.getMission();
    }

}
