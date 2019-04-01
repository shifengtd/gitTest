package cn.hxzy.service;

import java.util.List;
import cn.hxzy.pojo.ItemsCustom;
import cn.hxzy.pojo.ItemsQueryVo;



public interface ItemsService {
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	public ItemsCustom findItemsById(Integer id) throws Exception;
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
}
