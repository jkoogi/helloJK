package ex07객체디자인.ex703구체타입보다추상타입.sub;

import java.util.Queue;
import java.util.Stack;

public interface CargoShip {
	Stack<Supply> unload();
	Queue<Supply> load(Queue<Supply> supplies);
	int getRemainingCapacity();
}
