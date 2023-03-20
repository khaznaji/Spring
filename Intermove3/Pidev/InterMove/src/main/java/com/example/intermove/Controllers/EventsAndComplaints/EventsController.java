package com.example.intermove.Controllers.EventsAndComplaints;

import com.example.intermove.Entities.EventsAndComplaints.Events;
import com.example.intermove.Entities.EventsAndComplaints.ModaliteEvent;
import com.example.intermove.Entities.EventsAndComplaints.QRCode;
import com.example.intermove.Entities.EventsAndComplaints.TypeEvent;
import com.example.intermove.Services.EventsAndComplaints.*;
import com.example.intermove.Services.Files.FileStorageService;
import com.example.intermove.Services.UserService.UserService;

import com.github.sarxos.webcam.Webcam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Controller
@RestController
@RequestMapping("/Events")
public class EventsController {
    @Autowired
    private EventService service;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")

    public ResponseEntity<String> saveEvnets(@RequestParam("title") String title, @RequestParam("description") String desc,
                                             @RequestParam("dateD") Date dateD, @RequestParam("dateF") Date dateF,
                                             @RequestParam("nbPlace") int nbP, @RequestParam("Speaker") MultipartFile Speaker,
                                             @RequestParam("typeEvent") TypeEvent typeEvent, @RequestParam("modaliteEvent") ModaliteEvent modaliteEvent
            , @RequestParam("upload") MultipartFile upload) {
        Events events = new Events();
        events.setTitle(title);
        events.setDescription(desc);
        events.setDateD(dateD);
        events.setDateF(dateF);
        events.setSpeaker(fileStorageService.storeFile(Speaker));
        events.setTypeEvent(typeEvent);
        events.setModaliteEvent(modaliteEvent);
        events.setNbreDePlaces(nbP);
        events.setImage(fileStorageService.storeFile(upload));
        Integer id = service.saveEvents(events);
        return new ResponseEntity<String>("events with " + id + "has been saved", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Events>> getAllEventsdetails() {
        List<Events> list = service.getAllEvents();
        return new ResponseEntity<List<Events>>(list, HttpStatus.OK);
    }


    @PutMapping("/updateEvents/{id}")
    public ResponseEntity<String>  UpdateEvents( @RequestParam("title") String title , @RequestParam("description") String desc,
                                                @RequestParam("dateD") Date dateD, @RequestParam("dateF") Date dateF,
                                                @RequestParam("nbPlace") int nbP, @RequestParam("Speaker") String Speaker,
                                                @RequestParam("typeEvent") TypeEvent typeEvent, @RequestParam("modaliteEvent") ModaliteEvent modaliteEvent
            , @RequestParam("upload") String upload , @PathVariable Integer id ) {
        Events events = new Events();
        events.setTitle(title);
        events.setDescription(desc);
        events.setDateD(dateD);
        events.setDateF(dateF);
        events.setSpeaker(Speaker);
        events.setTypeEvent(typeEvent);
        events.setModaliteEvent(modaliteEvent);
        events.setNbreDePlaces(nbP);
        events.setImage(upload);
         service.UpdateEvent(events, id);
        return new ResponseEntity<String>("events with " + id + "has been saved", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteEvent(id);
    }

    @GetMapping("/getEventsbyid/{id} ")
    public ResponseEntity<Events> getEventsById(@PathVariable("id") Integer id) {
        Events ev = service.getEventsById(id);
        return new ResponseEntity<Events>(ev, HttpStatus.OK);

    }

    @GetMapping("/getEventsbyTitle/{title}")
    public List<Events> getEventsbyTitle(@PathVariable("title") String title) {
        return service.findByTitle(title);
    }

    @PostMapping(value = "/affecter-user-event/{id}/{idE}")
    public ResponseEntity<String> affecterUserToEvent(@PathVariable("id") int id, @PathVariable("idE") Integer idE) {

        // service.AssignUserToEvent(id, idE);

        try {
            service.assignUserToEvent(id, idE);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @Value("${twillio.fromPhoneNumber}")
    private String from;

    @Value("${twillio.toPhoneNo}")
    private String to;
    @Autowired
    private TwillioServiceImpl twillioService;
    @DeleteMapping(value = "/remove-user-event/{id}/{idE}")
    public ResponseEntity<String> RemoveaffecterUserToEvent(@PathVariable("id") int id, @PathVariable("idE") Integer idE) {

        // service.AssignUserToEvent(id, idE);

        try {
            service.removeUserFromEvent(id, idE);
            String body = "Annulation de " +id + "pour l'event" +idE ;
            twillioService.sendSms(to, from, body);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/videochat2")
    public ModelAndView videochat2() {
        return new ModelAndView("videochat2");
    }


    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping
    public QRCode createQRCode(@RequestBody String link) throws Exception {
        return qrCodeService.createQRCode(link);
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCodeImage(@PathVariable("id") Long id) throws IOException {
        byte[] image = qrCodeService.getQRCodeImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @GetMapping("/events/{eventId}/share/facebook")
    public String shareOnFacebook(@PathVariable Integer eventId, HttpServletRequest request) {
        Events ev = service.getEventsById(eventId);
        String eventUrl = request.getRequestURL().toString().replace("/share/facebook", "");
        String facebookUrl = "https://www.facebook.com/sharer/sharer.php?u=" + eventUrl;
        return "redirect:" + facebookUrl;
    }
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        return out.toByteArray();
    }
    @GetMapping("/export")
    public ResponseEntity<ByteArrayResource> exportEventsToExcel() throws IOException {
        List<Events> events = service.getAllEvents();
        ByteArrayInputStream in = EventExcelGenerator.eventsToExcel(events);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=events.xlsx");
        return ResponseEntity.ok().headers(headers).body(new ByteArrayResource(toByteArray(in)));
    }

    @GetMapping(value = "/video-feed", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getVideoFeed() throws IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage image = webcam.getImage();
        webcam.close();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(image, "jpg", baos);

        byte[] bytes = baos.toByteArray();

        // Changer le chemin ci-dessous pour le dossier souhaité
        String outputFolder = "C:/Users/DELL/Desktop/Pidev/InterMove/speaker/";
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String filePath = outputFolder + fileName;

        // Ecrire le contenu de l'image dans un fichier sur le disque
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(bytes);
        fos.close();

        return ResponseEntity.ok().body(bytes);
    }



}














