package com.kh.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.test.dto.Member;
import com.kh.test.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private final MemberMapper mapper;
	
	@Override
	public List<Member> selectAllList() {
		return mapper.selectAllList();
	}
}
