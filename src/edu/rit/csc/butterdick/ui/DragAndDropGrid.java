package edu.rit.csc.butterdick.ui;

public interface DragAndDropGrid<E>
{
	int getWidth();
	int getHeight();
	E get(int row, int col);
	E remove(int row, int col);
	void set(int row, int col, E cell);
	boolean notEmpty(int row, int col);
}
