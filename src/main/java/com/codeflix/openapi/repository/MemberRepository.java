package com.codeflix.openapi.repository;

import com.codeflix.openapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Integer> {
}
