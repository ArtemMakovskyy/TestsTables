package com.winestoreapp.controller;

import com.winestoreapp.model.Order;
import com.winestoreapp.model.OrderDeliveryInformation;
import com.winestoreapp.model.OrderPaymentStatus;
import com.winestoreapp.model.PurchaseObject;
import com.winestoreapp.model.ShoppingCard;
import com.winestoreapp.model.User;
import com.winestoreapp.model.Wine;
import com.winestoreapp.model.WineColor;
import com.winestoreapp.model.WineType;
import com.winestoreapp.repository.OrderDeliveryInformationRepository;
import com.winestoreapp.repository.OrderRepository;
import com.winestoreapp.repository.PurchaseObjectRepository;
import com.winestoreapp.repository.ShoppingCardRepository;
import com.winestoreapp.repository.UserRepository;
import com.winestoreapp.service.WineService;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
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
@Log4j2
@RequestMapping("/oto")
public class WineController {
    private final WineService wineService;
    private final UserRepository userRepository;
    private final PurchaseObjectRepository purchaseObjectRepository;
    private final ShoppingCardRepository shoppingCardRepository;
    private final OrderDeliveryInformationRepository orderDeliveryInformationRepository;
    private final OrderRepository orderRepository;


    @GetMapping("/pdb/{id}")
    public ResponseEntity<Resource> getWinePictureDb(@PathVariable Long id) throws IOException {
        return wineService.getPictureByIdFromDb(id);
    }

    @GetMapping("/pbp/{id}")
    public ResponseEntity<Resource> getWinePicturePath(@PathVariable Long id) throws IOException {
        return wineService.getPictureByIdByPath(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getEmployeePicture(
            @PathVariable Long id) throws IOException {
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

    @PostConstruct
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
        wineService.addRaring(1L, 1);
        wineService.addRaring(1L, 2);
        wineService.addRaring(1L, 3);
        wineService.addRaring(1L, 4);
        wineService.addRaring(1L, 5);
        wineService.addRaring(1L, 1);

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
        orderChaneCreating();
        return "ok";
    }

    private void orderChaneCreating() {
        createUser();
        createPurchaseObject1();
        createPurchaseObject2();
        createShoppingCard();
        createOrderDeliveryInformation();
        createOrder();
        queries();
    }

    private void queries() {
        orderRepository.findById(1L);
        final List<Order> all = orderRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i).getShoppingCard().getPurchaseObjects().get(1).getQuantity());
        }
    }

    private void createOrder() {
        Order order = new Order();
        order.setUser(userRepository.findById(1L).get());
        order.setShoppingCard(shoppingCardRepository.findById(1L).get());
        order.setDeliveryInformation(orderDeliveryInformationRepository.findById(1L).get());
        order.setPaymentStatus(OrderPaymentStatus.CREATED);
        order.setRegistrationTime(LocalDateTime.now());
        order.setCompletedTime(new Date());
        order.setLocalDate(LocalDate.now());
        order.setLocalTime(LocalTime.now());
        orderRepository.save(order);
    }

    private void createOrderDeliveryInformation() {
        OrderDeliveryInformation orderDeliveryInformation = new OrderDeliveryInformation();
        orderDeliveryInformation.setCity("Kyiv");
        orderDeliveryInformation.setStreet("Poleva");
        orderDeliveryInformation.setHouse(16);
        orderDeliveryInformation.setFloor(1);
        orderDeliveryInformation.setApartment(2);
        orderDeliveryInformation.setPhone("05012345678");
        orderDeliveryInformation.setAdditionally("addition info");
        orderDeliveryInformationRepository.save(orderDeliveryInformation);
    }

    private void createShoppingCard() {
        ShoppingCard shoppingCard = new ShoppingCard();
        // TODO: 03.02.2024 how to get list
        List<PurchaseObject> purchaseObjects = new ArrayList<>();
        purchaseObjects.add(purchaseObjectRepository.findById(1L).get());
        purchaseObjects.add(purchaseObjectRepository.findById(2L).get());
        shoppingCard.setPurchaseObjects(purchaseObjects);
        final BigDecimal totalCost = purchaseObjects.stream()
                .map(d -> d.getPrice().multiply(BigDecimal.valueOf(d.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        shoppingCard.setTotalCost(totalCost);
        shoppingCardRepository.save(shoppingCard);
    }

    private void createPurchaseObject1() {
        PurchaseObject purchaseObject = new PurchaseObject();
        final Wine wineById = wineService.getWineById(1L);
        purchaseObject.setWines(wineById);
        purchaseObject.setPrice(wineById.getPrice());
        purchaseObject.setQuantity(1);
        final PurchaseObject save = purchaseObjectRepository.save(purchaseObject);
    }

    private void createPurchaseObject2() {
        PurchaseObject purchaseObject = new PurchaseObject();
        final Wine wineById = wineService.getWineById(2L);
        purchaseObject.setWines(wineById);
        purchaseObject.setPrice(wineById.getPrice());
        purchaseObject.setQuantity(3);
        final PurchaseObject save = purchaseObjectRepository.save(purchaseObject);
    }

    private void createUser() {
        User user = new User();
        user.setName("Anton");
        final User savedUser = userRepository.save(user);
        log.info(savedUser);
    }
}
