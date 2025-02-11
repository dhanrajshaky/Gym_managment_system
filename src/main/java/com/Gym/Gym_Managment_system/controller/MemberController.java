package com.Gym.Gym_Managment_system.controller;

import com.Gym.Gym_Managment_system.model.Member;
import com.Gym.Gym_Managment_system.service.memberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    private memberService memberService;

    @PostMapping("/add")
    public Member addMember(@Valid  @RequestBody Member member){
        return memberService.addMember(member);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Member> membersPage = memberService.getAllMember(pageable);

        // Convert Page<Member> to List<MemberDTO>
        List<Member> memberDTOList = membersPage.stream()
                .map(member -> new Member(
                        member.getId(),
                        member.getName(),
                        member.getEmail(),
                        member.getMembershipPlan()))
                .collect(Collectors.toList());

        // Creating a map to send both list of members and totalPages for pagination
        Map<String, Object> response = new HashMap<>();
        response.put("members", memberDTOList);
        response.put("totalPages", membersPage.getTotalPages());

        return ResponseEntity.ok(response);
    }



    @PutMapping("/update-{id}")
    public ResponseEntity<Member> updatedMember(@PathVariable Long id , @Valid @RequestBody Member member){
        Member updatedMember = memberService.updateMember(id,member);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/delete-{id}")
    public  ResponseEntity<Void> deleteMember(@PathVariable Long id ){
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search-{keyword}")
    public List<Member> searchMember(@PathVariable("keyword") String keyword){
        return memberService.searchMember(keyword);
    }

    @GetMapping("/filter-{plan}")
    public List<Member> filterByMembershipPlan(@PathVariable("plan") String membershipPlan){
        return memberService.filterByMembershipPlan(membershipPlan);
    }

}
