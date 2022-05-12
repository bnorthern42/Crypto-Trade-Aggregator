package net.northern.tradeagg.controllers;

import net.northern.tradeagg.Util.CSVHelper;
import net.northern.tradeagg.Util.ResponseMessage;
import net.northern.tradeagg.models.Trades;
import net.northern.tradeagg.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class PostData {

    @Autowired
    private TradeService tradeService;

    @PostMapping(value = "/uploadKucoin", consumes={"multipart/form-data"})
    public ResponseEntity<ResponseMessage> pushTradesCSV(@RequestParam("file") MultipartFile file) throws UnsupportedEncodingException {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                tradeService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
