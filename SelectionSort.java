package Ajinkya.Sorts;

import Ajinkya.*;

public class SelectionSort implements Runnable{
	
	private Integer[] toBeSorted;
	private VisualizerFrame frame;

	
	public SelectionSort(Integer[] toBeSorted, VisualizerFrame frame) {
		this.toBeSorted = toBeSorted;
		this.frame = frame;
	}
	
	public void run() {
			sortSlow();
		sharestrarr();
		SortingVisualizer.isSorting=false;
	}
	
	public void sortSlow() {
		int temp = 0;
		int selected = 0;

		for(int i = 0; i<toBeSorted.length; i++)
		{
			selected = i;
			for(int j = toBeSorted.length-1; j>i; j--)
			{
				
				if (toBeSorted[j] <= toBeSorted[selected])
				{
					selected = j;
				}
				frame.reDrawArray(toBeSorted, selected, j-1);
				try
				{
					Thread.sleep(SortingVisualizer.sleep);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}				
			}
			temp = toBeSorted[i];
			toBeSorted[i] = toBeSorted[selected];
			toBeSorted[selected]= temp;
		}
		frame.reDrawArray(toBeSorted);
	}
	public void sharestrarr()
	{
		frame.getsrtSize(toBeSorted);
	}
}
