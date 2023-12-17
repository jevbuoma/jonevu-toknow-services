/**
 * 
 */
package com.jonevu.abi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.jonevu.abi.gameofchance.model.Entry;
import com.jonevu.abi.gameofchance.service.LottoPickGeneratorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
@RestController
@RequestMapping("/lotto")
public class GameOfChanceController {

	private static final Logger logger = LoggerFactory.getLogger(GameOfChanceController.class);

	@Autowired
    private LottoPickGeneratorService lottoPickGeneratorService;
    
    
    @GetMapping("/mega/{tickets}")
    public ResponseEntity<String> getMegaPicks(@PathVariable("tickets") int tickets) {
        logger.info("Getting Mega tickets - number of tickets to purchase: "+tickets);
        String megaPicks = lottoPickGeneratorService.pickMega(tickets);
        return new ResponseEntity<>(megaPicks, HttpStatus.OK);
    }

    @GetMapping("/mega/analysis/{inputInfo}")
    public ResponseEntity<String> megaAnalysis(@PathVariable("inputInfo") String inputInfo) {
        logger.info("Processing Mega Analysis - inputInfo: "+inputInfo);
        String megaPicks = lottoPickGeneratorService.getMegaAnalysis(inputInfo);
        return new ResponseEntity<>(megaPicks, HttpStatus.OK);
    }

    @GetMapping("/power-ball/{tickets}")
    public ResponseEntity<String> getPowerBallPicks(@PathVariable("tickets") int tickets) {
        logger.info("Getting Power Ball tickets - number of tickets to purchase: "+tickets);
        String powerBallPicks = lottoPickGeneratorService.pickPoewrBall(tickets);
        return new ResponseEntity<>(powerBallPicks, HttpStatus.OK);
    }

    @GetMapping("/power-ball/analysis/{inputInfo}")
    public ResponseEntity<String> powerballAnalysis(@PathVariable("inputInfo") String inputInfo) {
        logger.info("Processing Powerball Analysis - inputInfo: "+inputInfo);
        String powerBallPicks = lottoPickGeneratorService.getPowerballAnalysis(inputInfo);
        return new ResponseEntity<>(powerBallPicks, HttpStatus.OK);
    }

    @PostMapping("/rev/{tickets}")
    public ResponseEntity<String> getRevLottoPicks(@PathVariable("tickets") int tickets, @RequestBody Entry entry) {
        logger.info("Getting Reverse lottery tickets - number of tickets to purchase: "+tickets);
        entry.setNumOfPicks(tickets);
        String picks = lottoPickGeneratorService.pickRevLotto(entry);
        return new ResponseEntity<>(picks, HttpStatus.OK);
    } 
    
    @PostMapping("/reg/{tickets}")
    public ResponseEntity<String> getLottoPicks(@PathVariable("tickets") int tickets) {
        logger.info("Getting Illinois lottery tickets - number of tickets to purchase: "+tickets);
        String picks = lottoPickGeneratorService.pickRegLotto(tickets);
        return new ResponseEntity<>(picks, HttpStatus.OK);
    }

    @PostMapping("/reg/analysis/{inputInfo}")
    public ResponseEntity<String> lottoAnalysis(@PathVariable("inputInfo") String inputInfo) {
        logger.info("Processing Lotto Analysis - inputInfo: "+inputInfo);
        String picks = lottoPickGeneratorService.getLottoAnalysis(inputInfo);
        return new ResponseEntity<>(picks, HttpStatus.OK);
    }

    @GetMapping("/more/{inputInfo}")
    public ResponseEntity<String> lottoMore(@PathVariable("inputInfo") String inputInfo) {
        logger.info("Processing Lotto More - inputInfo: "+inputInfo);
        String moreLotto = lottoPickGeneratorService.getLottoMore(inputInfo);
        return new ResponseEntity<>(moreLotto, HttpStatus.OK);
    }
}
