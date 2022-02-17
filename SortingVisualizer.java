package Ajinkya;

import java.util.ArrayList;		//Dynamic Array
import java.util.Collections;

import Ajinkya.Sorts.*;

public class SortingVisualizer 
{
	private static Thread sortingThread;

	public static VisualizerFrame frame;
	public static Integer[] toBeSorted;
	public static boolean isSorting = false;
	public static int sortDataCount = 40;
	public static int sleep = 20;
	public static int blockWidth;



	public static void main(String[] args) 
	{
		frame = new VisualizerFrame();
		resetArray();
		frame.setLocationRelativeTo(null);
	}

	public static  void sharearr()
	{
		frame.getarrSize(toBeSorted);
	}


	public static void resetArray()
	{
		if (isSorting) return;
		toBeSorted = new Integer[sortDataCount];
		blockWidth = (int) (Math.floor(800/sortDataCount));				//closest integer val

		for(int i = 0; i<toBeSorted.length; i++)
		{
				toBeSorted[i] = (int) (sortDataCount*Math.random());	//returns double i.e. >0 and <1
		}

		frame.preDrawArray(toBeSorted);
	}

	public static void startSort(String type)
	{
		if (sortingThread == null || !isSorting)
		{
			resetArray();
			isSorting = true;

			switch(type){
			case "Bubble":
				sharearr();
				sortingThread = new Thread(new BubbleSort(toBeSorted, frame));
				break;

			case "Selection":
				sharearr();
				sortingThread = new Thread(new SelectionSort(toBeSorted, frame));
				break;

			case "Insertion":
				sharearr();
				sortingThread = new Thread(new InsertionSort(toBeSorted, frame));
				break;

			case "Merge":
				sharearr();
				sortingThread = new Thread(new MergeSort());
				break;

			default:
				isSorting = false;
				return;

			}
			sortingThread.start();
		}
	}

}