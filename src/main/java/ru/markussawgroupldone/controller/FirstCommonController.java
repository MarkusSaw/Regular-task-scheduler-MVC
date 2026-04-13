package ru.markussawgroupldone.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.markussawgroupldone.Service.RecordService;
import ru.markussawgroupldone.entity.DTO.RecordsContainerAndStatisticDto;
import ru.markussawgroupldone.entity.Record;
import ru.markussawgroupldone.entity.RecordStatus;

import java.util.List;

@Controller
public class FirstCommonController {
    private final RecordService recordService;

@Autowired
    public FirstCommonController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/")
    public String redirectToHomePage(){
        return "redirect:/home";
    }


    @RequestMapping("/home")
    public String getMainPage(Model model, @RequestParam(name = "filter", required = false) String filterMode){

        RecordsContainerAndStatisticDto container = recordService.allRecords(filterMode);
        model.addAttribute("numberOfDoneRecords",container.getNumberOfDoneRecords());
        model.addAttribute("numberOfActiveRecords",container.getNumberOfActiveRecords());
        model.addAttribute("records",container.getRecordsForDto());
        return "main-page";
    }

//        @RequestMapping("/pepi")
//        public String getPepe(){
//            return "pepe";
//    }




    @RequestMapping(value = "/add-record", method = RequestMethod.POST)
    public String addRecord(@RequestParam String title) {
        recordService.saveRecord(title);
        return "redirect:/home";
    }

    @RequestMapping(value = "/made-it-record", method = RequestMethod.POST)
    public String MadeItRecord(@RequestParam int id,
                               @RequestParam(name = "filter", required = false)String filterMode) {
        recordService.updateRecordStatus(id, RecordStatus.DONE);
        return "redirect:/home"+(filterMode != null && !filterMode.isBlank() ? "filter="+ filterMode : "");
    }

    @RequestMapping(value = "/delete-excess-record", method = RequestMethod.POST)
    public String DeleteExcessRecord(@RequestParam int id,
                                     @RequestParam(name = "filter", required = false)String filterMode) {

        recordService.deleteExcessRecord(id);
        return "redirect:/home"+(filterMode != null && !filterMode.isBlank() ? "filter="+ filterMode : "");
    }



}



