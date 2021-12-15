package com.betaplan.nardi.post_and_likes.controllers;

import com.betaplan.nardi.post_and_likes.models.Like;
import com.betaplan.nardi.post_and_likes.models.Post;
import com.betaplan.nardi.post_and_likes.models.User;
import com.betaplan.nardi.post_and_likes.services.LikeService;
import com.betaplan.nardi.post_and_likes.services.PostService;
import com.betaplan.nardi.post_and_likes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/post/create")
    public String newPost(@ModelAttribute("newPost") Post post, HttpSession session, Model model){
        Long userId = ((Long) session.getAttribute("userId"));
        if(userId==null){
            return "redirect:/login";
        }else{
            User authenticatedUser = userService.findUserById(userId);
            model.addAttribute("user", authenticatedUser);
            return "posts/new";
        }
    }
    @PostMapping("/post/create")
    public String createPost(@Valid @ModelAttribute("newPost")Post post, BindingResult result,HttpSession session){
        if(result.hasErrors()){
            return "posts/new";
        }else {
            Long userId = ((Long) session.getAttribute("userId"));
            User authenticatedUser = userService.findUserById(userId);
            post.setUser(authenticatedUser);
            postService.createUpdatePost(post);
            return "redirect:/posts";
        }
    }
    @GetMapping("/posts")
    public String viewAllPosts(@ModelAttribute("like")Like like,HttpSession session, Model model){
        Long userId = ((Long) session.getAttribute("userId"));
        if(userId==null){
            return "redirect:/login";
        }else{
            User authenticatedUser = userService.findUserById(userId);
            model.addAttribute("user", authenticatedUser);
            model.addAttribute("posts",postService.findAllPosts());

            return "posts/posts";
        }
    }
    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable("id")Long id){
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("/post/{id}/details")
    public String viewPost(@ModelAttribute("like") Like like, Model model, @PathVariable("id")Long postId, HttpSession session){
        Long userId = ((Long) session.getAttribute("userId"));
        if(userId==null){
            return "redirect:/login";
        }else{
            model.addAttribute("post",postService.findPost(postId));
            model.addAttribute("user",userService.findUserById(userId));
            return "posts/details";
        }
    }


    @PostMapping("/post/{id}/like")
    public String likePost(@ModelAttribute("like")Like like,@PathVariable("id")Long postId,HttpSession session,Model model){
        Long userId = ((Long) session.getAttribute("userId"));
        likeService.createLike(postId,userId);
        return "redirect:/posts";
    }
    @DeleteMapping("/post/{id}/dislike")
    public String dislike(@PathVariable("id")Long postId,HttpSession session){
        Long userId = ((Long) session.getAttribute("userId"));
        Like like=likeService.findLike(postId,userId);
        likeService.deleteLike(like.getId());
        return "redirect:/posts";
    }

    @GetMapping("/post/{id}/edit")
    public String viewEditPost(@ModelAttribute("post")Post post,@PathVariable("id")Long postId,HttpSession session,Model model){
        Long userId = ((Long) session.getAttribute("userId"));
        if(userId==null){
            return "redirect:/login";
        }else{

            post= postService.findPost(postId);
            model.addAttribute("post",post);
            return "posts/edit";
        }

    }
    @PutMapping("/post/{id}/edit")
    public  String editPost(@Valid @ModelAttribute("post")Post post,BindingResult result){
        if (result.hasErrors()) {
            return "posts/edit";
        } else {
            postService.createUpdatePost(post);
            return "redirect:/post/{id}/edit";
        }
    }

}
