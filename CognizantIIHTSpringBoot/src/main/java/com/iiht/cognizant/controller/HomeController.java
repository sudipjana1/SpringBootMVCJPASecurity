package com.iiht.cognizant.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iiht.cognizant.model.Book;
import com.iiht.cognizant.model.Subject;
import com.iiht.cognizant.model.User;
import com.iiht.cognizant.model.UserRegistration;
import com.iiht.cognizant.model.UserRole;
import com.iiht.cognizant.service.BookService;
import com.iiht.cognizant.service.MyUserDetailsService;
import com.iiht.cognizant.service.SubjectService;
import com.iiht.cognizant.service.UserService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired BookService bookService;
	@Autowired SubjectService subjectService;
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	    
	}
	
	/*
	 * 
	 * 
	 */
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserRegistration());


        return "registration";
    }
	
	@RequestMapping(value = "/saveregistration", method = RequestMethod.POST)
	    public String registration(@ModelAttribute("userForm") UserRegistration userForm, Model model) {
	        //userValidator.validate(userForm, bindingResult);
		 	System.out.println(userForm.getUser().getUsername()+":"+userForm.getUserRole().getRole()+":");

//	        if (bindingResult.hasErrors()) {
//	            return "registration";
//	        }
		 	
		 	userForm.getUser().setEnabled(true);
			Set<UserRole> userRoles = new HashSet<UserRole>(0);
			User user = new User();
			user.setUsername(userForm.getUser().getUsername());
			UserRole userRole = new UserRole(user, userForm.getUserRole().getRole());
			userRoles.add(userRole);
		 	userForm.getUser().setUserRole(userRoles);
		 	System.out.println(userForm.getUser().toString());
		 	
		 	userService.save(userForm.getUser());

	        return "redirect:/login";
	    }
	 
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET) 
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		/*model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");*/
		model.setViewName("login");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		System.out.println("&&&&&&&&&&&&&");

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView home(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		System.out.println("Hello After login");
		System.out.println("&&&&&&&&&&&&&");

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("home");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	/*
	*
	*
	*
	*/
	 
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/user/searchbook", method = RequestMethod.GET)
	public String searchBook(@ModelAttribute Book book,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		if(book.getBookId() != 0){
			Optional<Book> searchbook = null;
			searchbook = bookService.searchBookbyId(book.getBookId());
			if (!searchbook.isPresent()) {
				model.addAttribute("bookerror", "No Book with this ID" );
			}else {
				model.addAttribute("book", searchbook );
			}
		}
		model.addAttribute("serverTime", formattedDate );
		return "searchbook";
	}
	
	@RequestMapping(value = "/user/searchbookbytitle", method = RequestMethod.GET)
	public String searchBookByTitle(@ModelAttribute Book book,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		if(book.getTitle() != null){
			List<Book> searchbook = bookService.getBookByTitle(book.getTitle());
			if (searchbook.isEmpty()) {
				model.addAttribute("bookerror", "No Book with this Title" );
			}else {
				model.addAttribute("books", searchbook );
			}
		}
		model.addAttribute("serverTime", formattedDate );
		return "searchbookbytitle";
	}
	
	@RequestMapping(value = "/user/deletebook", method = RequestMethod.GET)
	public String deleteBook(@ModelAttribute Book book,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		if(book.getBookId() != 0){
			if (!bookService.deleteBookbyId(book.getBookId())) {
				model.addAttribute("bookerror", "No Book with this ID" );
			}else {
				model.addAttribute("bookerror", "Book Deleted" );
			}
		}
		model.addAttribute("serverTime", formattedDate );
		return "deletebook";
	}
	
	
	
	@RequestMapping(value = "/user/addbook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book, Model model, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		System.out.println(book.toString());
		bookService.addBook(book);
		model.addAttribute("serverTime", formattedDate );

		
		return "home";
	}
	
	@RequestMapping(value="/user/addbook")
	public String addBook(Model model, Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("book", new Book());

		return "addbook";
	}
	
	@RequestMapping(value="/admin/addsubject")
	public String addSubject(Model model, Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		Subject subject = new Subject();
		List<Book> books = new ArrayList<Book>();
		Book book1 = new Book();
		Book book2 = new Book();
		book1.setBookId(1);
		book2.setBookId(2);
		books.add(book1);
		books.add(book2);
		subject.setReferences(books);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("subject", subject);

		return "addsubject";
	}	
	
	
	@RequestMapping(value = "/admin/addsubject", method = RequestMethod.POST)
	public String saveSubject(@ModelAttribute Subject subject, Model model, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("",subject.toString());
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		System.out.println(subject.toString());
		List<Book> sbooks = new ArrayList<Book>();
		List<Book> books = new ArrayList<Book>();
		sbooks=subject.getReferences();
		Iterator<Book> it = sbooks.iterator();
		while(it.hasNext()) {
			long bookId = it.next().getBookId();
			Optional<Book> book = bookService.searchBookbyId(bookId);
			books.add(book.get());

		}
		subject.setReferences(books);
		subjectService.addSubject(subject);
		model.addAttribute("serverTime", formattedDate );

		
		return "home";
	}
	
	@RequestMapping(value = "/admin/searchsubject", method = RequestMethod.GET)
	public String searchSubject(@ModelAttribute Subject subject,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		if(subject.getSubjectId() != 0){
			Optional<Subject> searchsub = null;
			searchsub = subjectService.searchSubjectbyId(subject.getSubjectId());
			if (!searchsub.isPresent()) {
				model.addAttribute("suberror", "No Subject with this ID" );
			}else {
				model.addAttribute("subject", searchsub );
			}
		}
		model.addAttribute("serverTime", formattedDate );
		//subjectService.findSubjectByDuration(200);
		return "searchsubject";
	}
	
	@RequestMapping(value = "/admin/searchsubjectbyduration", method = RequestMethod.GET)
	public String searchSubjectByDuration(@ModelAttribute Subject subject,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		if(subject.getDurationInHours() != 0){
			List<Subject> searchsub = subjectService.findSubjectByDuration(subject.getDurationInHours());
			if (searchsub.isEmpty() ) {
				model.addAttribute("suberror", "No Subject with this Duration" );
			}else {
				model.addAttribute("subjects", searchsub );
			}
		}
		model.addAttribute("serverTime", formattedDate );
		//subjectService.findSubjectByDuration(200);
		return "searchsubjectbyduration";
	}
	
	@RequestMapping(value = "/admin/deletesubject", method = RequestMethod.GET)
	public String deleteBook(@ModelAttribute Subject subject,Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		if(subject.getSubjectId()!= 0){
			if (!subjectService.deleteSubjectbyId(subject.getSubjectId())) {
				model.addAttribute("suberror", "No Subject with this ID" );
			}else {
				model.addAttribute("suberror", "Subject Deleted" );
			}
		}
		model.addAttribute("serverTime", formattedDate );
		return "deletesubject";
	}
}
