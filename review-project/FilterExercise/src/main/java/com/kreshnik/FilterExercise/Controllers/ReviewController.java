package com.kreshnik.FilterExercise.Controllers;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kreshnik.FilterExercise.IServices.IReviewService;
import com.kreshnik.FilterExercise.Models.Review;

@Controller
public class ReviewController {

        @Autowired
        private IReviewService _reviewService;

         @GetMapping("/")
         public String Home(){
                 return "review";
         }

        @PostMapping("/")
        public String HomePage(Model model, int minRating, Boolean highestRatingFirst,
                        Boolean newestFirst, Boolean prioritizeByText) {
                Predicate<Review> filterByRating = r -> (r.getRating() >= minRating);

                Comparator<Review> sortByRating = highestRatingFirst
                                ? Comparator.comparing(Review::getRating)
                                : Comparator.comparing(Review::getRating).reversed();

                Comparator<Review> sortByDate = newestFirst ? Comparator.comparing(Review::getReviewCreatedOnDate)
                                : Comparator.comparing(Review::getReviewCreatedOnDate).reversed();

                var reviewList = _reviewService.GettAllReviews();

                var filteredList = reviewList.stream().filter(filterByRating).collect(Collectors.toList());

                var orderedList = prioritizeByText
                                ? filteredList.stream()
                                                .sorted(sortByRating.thenComparing(sortByDate)
                                                                .thenComparing(Review::getReviewText))
                                : filteredList.stream().sorted(sortByRating.thenComparing(sortByDate));

                model.addAttribute("reviewList", orderedList.collect(Collectors.toList()));
                return "review";
        }
}
