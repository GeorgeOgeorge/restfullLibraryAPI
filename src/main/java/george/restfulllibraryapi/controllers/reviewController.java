package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.dtos.responses.ReviewResponse;
import george.restfulllibraryapi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

@RestController
@RequestMapping(value = "/review")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class reviewController {

    private ReviewService reviewService;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = this.reviewService;
    }

    @GetMapping
    public List<ReviewResponse> list(){
        return this.reviewService.list();
    }

}
