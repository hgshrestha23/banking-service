package com.rab3tech.admin.ui.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rab3tech.admin.service.CustomerSecurityQuestionsService;
import com.rab3tech.vo.LoginVO;
import com.rab3tech.vo.SecurityQuestionsVO;

@Controller
@RequestMapping("/admin")
//http:localhost:4055
//httt://www.kuebikobank.com/admin/security/questions
public class AdminUIController {
	
	@Autowired
	private CustomerSecurityQuestionsService securityQuestionsService;
	
	@GetMapping("/security/questions")
	public String showSecurityQuestions(Model model) {
		List<SecurityQuestionsVO> questionsVOs=securityQuestionsService.findSecurityQuestions();
		model.addAttribute("questionsVOs",questionsVOs);
		return "admin/securityQuestions";
	}
	
	@GetMapping("/security/uquestion")
	public String updateSecurityQuestionStatus(String status,int qid,Model model) {
		securityQuestionsService.updateStatus(status, qid);
		return "redirect:/admin/security/questions";
	}
	
	
	@PostMapping("/add/question")
	public String addSecurityQuestion(String question,HttpSession session,Model model) {
		LoginVO  loginVO=(LoginVO)session.getAttribute("userSessionVO");
		String loginid=loginVO.getUsername();
		securityQuestionsService.addSecurityQuestion(question,loginid);
		return "redirect:/admin/security/questions";
	}
	

}
