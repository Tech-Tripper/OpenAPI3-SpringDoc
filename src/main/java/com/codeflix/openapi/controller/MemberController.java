package com.codeflix.openapi.controller;


import com.codeflix.openapi.entity.Member;
import com.codeflix.openapi.error_Model.ErrorObject;
import com.codeflix.openapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?>allMembers(){
        return ResponseEntity.ok(memberRepository.findAll());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> byId(@PathVariable(value = "id")Integer id){
        try{
            Optional<Member> opt = memberRepository.findById(id);
            if(opt.isPresent()){
                return ResponseEntity.ok(opt.get());
            } else {
                return notFound();
            }
        }catch (Exception exp){
            return badRequest(exp);
        }
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody Member member){
        try{
            Optional< Member> optionalMember = memberRepository.findById(member.getId());
            if (optionalMember.isPresent()){
                return  conflict();
            }else {
                return ResponseEntity.ok(memberRepository.save(member));
            }
            
        }catch (Exception exp){
            return  badRequest(exp);
            
        }
    }

    private ResponseEntity<?> conflict() {
        return new ResponseEntity<>( new ErrorObject(HttpStatus.CONFLICT.toString(), "Member already exist"), HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> notFound() {
        return new ResponseEntity<>( new ErrorObject(HttpStatus.NOT_FOUND.toString(), "Member not found"), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> badRequest( Throwable throwable) {
        return new ResponseEntity<>( new ErrorObject(HttpStatus.BAD_REQUEST.toString(), "Bad Request"), HttpStatus.BAD_REQUEST);
    }
}