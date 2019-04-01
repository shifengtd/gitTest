package cn.hxzy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.hxzy.pojo.Items;
import cn.hxzy.pojo.ItemsCustom;
import cn.hxzy.pojo.ItemsQueryVo;
import cn.hxzy.service.ItemsService;

@Controller
// 为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如：商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	ItemsService itemsService;

//	@RequestMapping("/queryItems")
//	public ModelAndView queryItems() throws Exception {
//		// 调用service查找 数据库，查询商品列表
//		List<ItemsCustom> itemsList = new ArrayList<ItemsCustom>();
//		itemsList = itemsService.findItemsList(null);
//
//		// 返回ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		// 相当 于request的setAttribute，在jsp页面中通过itemsList取数据
//		modelAndView.addObject("itemsList", itemsList);
//
//		// 指定视图
//		modelAndView.setViewName("items/itemsList");
//
//		return modelAndView;
//	}
	
	/**
	 * 1,spring帮我们创建ItemsQueryVo对象，调用默认构造器.
	 * 2,根据传入的input name串，springmvc应用反射，直接赋值.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo vo) throws Exception {
		
		
		// 调用service查找 数据库，查询商品列表
		List<ItemsCustom> itemsList = new ArrayList<ItemsCustom>();
		
		if(vo.getItemsCustom() == null){
			itemsList = itemsService.findItemsList(null);
			
		}else{
			
			itemsList = itemsService.findItemsList(vo);
		}
		

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribute，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定视图
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}

	// @RequestMapping("/editItems")
	// 限制http请求方法，可以post和get
	// @RequestMapping(value = "/editItems", method = { RequestMethod.GET,
	// RequestMethod.POST })
	// public ModelAndView editItems() throws Exception {
	// ItemsCustom itemsCustom = itemsService.findItemsById(1);
	// ModelAndView modelAndView = new ModelAndView();
	// modelAndView.addObject("itemsCustom", itemsCustom);
	// modelAndView.setViewName("/items/editItems");
	// return modelAndView;
	// }

	// @RequestMapping(value = "/editItems", method = RequestMethod.GET)
	// public String editItems(HttpServletRequest request) throws Exception {
	// ItemsCustom itemsCustom = itemsService.findItemsById(1);
	// // 将数据保存到request中，在jsp页面中通过itemsCustom取数据
	// request.setAttribute("itemsCustom", itemsCustom);
	// return "/items/editItems";
	// }

	/**
	 * HttpServletRequest HttpServletResponse HttpSession Model
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/editItems")
	// public String editItems(Model model) throws Exception {
	// ItemsCustom itemsCustom = itemsService.findItemsById(1);
	// // 将数据保存到request中，在jsp页面中通过itemsCustom取数据
	// model.addAttribute("itemsCustom", itemsCustom);
	//
	// return "/items/editItems";
	// }

	@RequestMapping("/editItems")
	public String editItems(Model model, @RequestParam(value = "id", defaultValue = "1", required = false) Integer idd)
			throws Exception {

		System.out.println("id == " + idd);
		ItemsCustom itemsCustom = itemsService.findItemsById(idd);
		// 将数据保存到request中，在jsp页面中通过itemsCustom取数据
		model.addAttribute("itemsCustom", itemsCustom);

		return "/items/editItems";
	}

	// "myParam", "!myParam", or "myParam=myValue"
	// @RequestMapping(value = "/editItemsSubmit", params = "price=123")
	// public ModelAndView editItemsSubmit(int id, String name, float price)
	// throws Exception {
	// // itemsService.updateItems(id, itemsCustom);
	//
	// System.out.println("name=" + name);
	// System.out.println("price=" + price);
	// ModelAndView modelAndView = new ModelAndView();
	//
	// modelAndView.setViewName("success");
	// return modelAndView;
	// }

	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(ItemsCustom  items) throws Exception {

		System.out.println(items);
		
		this.itemsService.updateItems(items.getId(), items);
		
		
		return "success";
	}
	
	@RequestMapping("/deleteItems")
	public String  deleteItems(Integer[] items_id){
		for (Integer integer : items_id) {
			
			System.out.println("id == " + integer);
		}
		
		
		System.out.println();
		
		return "success";
		
		
	}
	
	
	@RequestMapping("/updateItems")
	public String  updateItems(ItemsQueryVo vo){
		
		
		System.out.println( " list == " + vo.getItemsCustomList());
		
		return  "success";
		
		
	}
	

	/**
	 * 重定向
	 * 
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping("/editItemsSubmit")
	// public String editItemsSubmit() throws Exception {

	// return "redirect:queryItems.action";
	// }

	// @RequestMapping("/editItemsSubmit")
	// controller方法返回string,请求转发
	// public String editItemsSubmit() throws Exception {

	// return "forward:queryItems.action";

	// }
}
