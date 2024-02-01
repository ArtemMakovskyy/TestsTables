package com.winestoreapp.controller;

import com.winestoreapp.model.Wine;
import com.winestoreapp.model.WineColor;
import com.winestoreapp.model.WineType;
import com.winestoreapp.service.WineService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oto")
public class WineController {
    private final WineService wineService;

    @GetMapping("/p{id}")
    public ResponseEntity<Resource> getWinePicture(@PathVariable Long id) throws IOException {
        return wineService.getPictureById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getEmployeePicture(@PathVariable Long id) throws IOException {
        final Wine wineById = wineService.getWineById(id);
        if (wineById != null) {
            final byte[] picture = wineById.getPicture();

            final ByteArrayResource byteArrayResource = new ByteArrayResource(picture);

            return ResponseEntity.ok()
                    .header("fileName", "originFileName.jpg")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(byteArrayResource);
        }
        throw new RemoteException();
    }

    @PostMapping
    public ResponseEntity<String> addPictureToWine(
            @RequestParam Long id,
            @RequestParam("file") MultipartFile multipartFile) {
        wineService.addPictureIntoDisc(id, multipartFile);
        return ResponseEntity.ok("download picture by id " + id);
    }

    @GetMapping
    public String start() {
        final Wine wine = wineService.create(
                "wow wine",
                220.5,
                "grape",
                true,
                WineType.SWEET,
                15.1,
                18.5,
                WineColor.RED,
                "colorDescribing",
                "taste",
                "aroma",
                "gastronomy",
                "description"
        );

        System.out.println(wine);
        wineService.addRaring(1L, 5);
        wineService.addRaring(1L, 5);
        wineService.addRaring(1L, 5);

        final Wine wine2 = wineService.create(
                "wow wine2",
                230.5,
                "grap2",
                true,
                WineType.DRY,
                35.1,
                38.5,
                WineColor.WHITE,
                "colorDescribing2",
                "taste2",
                "aroma2",
                "gastronomy2",
                "description2"
        );
        System.out.println(wine2);
        wineService.addRaring(2L, 5);
        wineService.addRaring(2L, 5);
        wineService.addRaring(2L, 5);
        return "ok";
    }
}
