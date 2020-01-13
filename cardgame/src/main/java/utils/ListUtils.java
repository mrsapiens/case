package utils;

import java.util.List;


public class ListUtils<T> {
	
	public void moveElements(List<T> listFrom, List<T> listTarget, int quantity) {
		for (int i = 0; i < quantity; i++) {
			T movedElement = listFrom.remove(0);
			listTarget.add(movedElement);
		}
	}
}
