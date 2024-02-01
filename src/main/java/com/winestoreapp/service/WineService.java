package com.winestoreapp.service;

import com.winestoreapp.model.Wine;
import com.winestoreapp.model.WineColor;
import com.winestoreapp.model.WineType;
import com.winestoreapp.repository.WineRepository;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class WineService {
    private static final String SAVE_PATH = "src/main/resources/static/";
    private final WineRepository wineRepository;


    public Wine create(
            String name,
            double price,
            String grape,
            Boolean decantation,
            WineType wineType,
            double strengthFrom,
            double strengthTo,
            WineColor wineColor,
            String colorDescribing,
            String taste,
            String aroma,
            String gastronomy,
            String description
    ) {
        Wine wine = new Wine();
        wine.setName(name);
        List<Integer> ratings = new ArrayList<>();
        wine.setRatings(ratings);
        wine.setPrice(BigDecimal.valueOf(price));
        wine.setGrape(grape);
        wine.setDecantation(decantation);
        wine.setWineType(wineType);
        wine.setStrengthFrom(BigDecimal.valueOf(strengthFrom));
        wine.setStrengthTo(BigDecimal.valueOf(strengthTo));
        wine.setWineColor(wineColor);
        wine.setColorDescribing(colorDescribing);
        wine.setTaste(taste);
        wine.setAroma(aroma);
        wine.setGastronomy(gastronomy);
        wine.setDescription(description);
        wine.setAverageRatingScore(BigDecimal.valueOf(0));
        final Wine save = wineRepository.save(wine);
        System.out.println(save);
        return save;
    }

    public void addRaring(Long wineId, Integer rating) {
        final Wine wine = wineRepository.findById(wineId)
                .orElseThrow(() -> new RuntimeException("Can't get wine by id " + wineId));
//        if (wine.getRatings().isEmpty()) {
//            List<Integer> ratings = new ArrayList<>();
//            wine.setRatings(ratings);
//        }
        wine.getRatings().add(rating);
        final BigDecimal averageRatingScore =
                BigDecimal.valueOf(
                        calculateAverageRatingScore(wine.getRatings())
                ).setScale(2, RoundingMode.HALF_UP);

        wine.setAverageRatingScore(averageRatingScore);
        Wine save = wineRepository.save(wine);
        System.out.println(save);
    }

    private double calculateAverageRatingScore(List<Integer> ratings) {
        final double numberOfRatingsAsPercentage = ratings.size() / (double) 100;
        return ratings.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.00) + numberOfRatingsAsPercentage;
    }

    public void addPictureIntoDisc(Long id, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("Please select the file");
        }
        Wine wine = wineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find by id " + id));
        try {
            final byte[] pictureBytes = multipartFile.getBytes();
            wine.setPicture(pictureBytes);
            wineRepository.save(wine);

            String filePath = SAVE_PATH + multipartFile.getOriginalFilename();
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(pictureBytes);
                System.out.println("Изображение успешно сохранено в файл: " + filePath);
            } catch (IOException e) {
                System.err.println("Ошибка при сохранении изображения в файл: " + e.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error picture saving");
        }
    }

    public Wine getWineById(Long id) {
        return wineRepository.findById(id).orElseThrow();
    }

    public ResponseEntity<Resource> getPictureById(Long id) throws IOException {
        Wine wine = getWineById(id);
        byte[] picture = wine.getPicture();

        ByteArrayResource byteArrayResource = new ByteArrayResource(picture);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=originFileName.jpg");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(byteArrayResource);
    }
}
