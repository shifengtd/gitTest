package cn.hxzy.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.hxzy.dao.ItemsDao;
import cn.hxzy.pojo.ItemsCustom;
import cn.hxzy.pojo.ItemsQueryVo;

@Repository
public class ItemsDaoImpl implements ItemsDao {

	private static List<ItemsCustom> itemList = null;

	static {
		itemList = new ArrayList<ItemsCustom>();

		itemList.add(new ItemsCustom(1, "冰箱", 5000.0f, "D://io", new Date(), "冰箱质量好价格低"));
		itemList.add(new ItemsCustom(2, "洗衣机", 2000.0f, "D://io", new Date(), "洗衣机质量好价格低"));
		itemList.add(new ItemsCustom(3, "笔记本电脑", 8000.0f, "D://io", new Date(), "笔记本电脑质量好价格低"));
		itemList.add(new ItemsCustom(4, "空调", 2000.0f, "D://io", new Date(), "空调质量好价格低"));
		itemList.add(new ItemsCustom(5, "彩电", 6000.0f, "D://io", new Date(), "彩电质量好价格低"));
		itemList.add(new ItemsCustom(6, "冰箱", 6000.0f, "D://io", new Date(), "彩电质量好价格低"));
	}

	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		
		//System.out.println("itemsQueryVo == " + itemsQueryVo);
		
		if(itemsQueryVo ==   null){
			return itemList;
			
			
		}
		
		
		List<ItemsCustom> result = new ArrayList<ItemsCustom>();
		// ItemsCustom itemsCustom = itemsQueryVo.getItemsCustom();

		String queryName = itemsQueryVo.getItemsCustom().getName();
		for (ItemsCustom item : itemList) {

			String name = item.getName();

			if (name.equals(queryName)) {

				result.add(item);

			}

		}

		// boolean flag = itemList.contains(itemsCustom);

		// if(flag){
		// result.add(itemsCustom);
		// }
		
	   System.out.println("result == " + result);

		return result;
	}

	public ItemsCustom findItemsById(Integer id) throws Exception {

		ItemsCustom itemsCustom = itemList.get(id - 1);

		return itemsCustom;
	}

	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {

		itemList.set(id - 1, itemsCustom);
	}

	public static void main(String[] args) {
		ItemsDaoImpl dao = new ItemsDaoImpl();

		ItemsQueryVo vo = new ItemsQueryVo();

		ItemsCustom itemsCustom = new ItemsCustom();

		itemsCustom.setName("冰箱");
		vo.setItemsCustom(itemsCustom);

		try {
			List result = dao.findItemsList(vo);

			System.out.println("result == " + result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
