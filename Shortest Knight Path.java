import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Chess {
    public static ArrayList<Integer> arrMain = new ArrayList<>();
    public static int d = 1;
    public static int knight(String start, String  finish)
    {
      arrMain.clear();
      d = 1;
      int[] st = charConverter(start);
      int[] fn = charConverter(finish);
      System.out.println(Arrays.toString(st) + " " + Arrays.toString(fn));
      if((Math.abs(st[0]-fn[0]))>3 || (Math.abs(st[1]-fn[1]))>3) d =2;
      if(st[0]!=fn[0] || st[1]!=fn[1])
      {
        outOfRange(st,fn,1);
        Collections.sort(arrMain);
        return arrMain.get(0);
      }
      return 0;
    }
  
    public static void outOfRange(int[] s,int[] f,int moves)
    {
      List<int[]> list = movement(s,f);
      int depth = moves;
      if(depth>6) return;//edit2
      for(int i=0;i<list.size();i++)
      {
        int[] tmparr = list.get(i);
        if((tmparr[0]!=f[0]) || (tmparr[1]!=f[1])) outOfRange(tmparr,f,depth+1);
        else arrMain.add(0+depth);
      }
    }
  
  
    	public static int[] charConverter(String s)
    {
      char[] carr = s.toCharArray();
      return new int[]{(carr[0]-96),(carr[1]-48)};
    }
  
    public static List<int[]> movement(int[] pos,int[] fin)
    {
      ArrayList<int[]> list = new ArrayList<>();
      list.add(new int[]{pos[0]-1,pos[1]-2});
      list.add(new int[]{pos[0]+1,pos[1]-2});
      list.add(new int[]{pos[0]+2,pos[1]-1});
      list.add(new int[]{pos[0]+2,pos[1]+1});
      list.add(new int[]{pos[0]+1,pos[1]+2});
      list.add(new int[]{pos[0]-1,pos[1]+2});
      list.add(new int[]{pos[0]-2,pos[1]+1});
      list.add(new int[]{pos[0]-2,pos[1]-1});
      list.removeIf(iarr -> iarr[0]<=0 || iarr[1]<=0);
      list.removeIf(iarr -> iarr[0]>8 || iarr[1]>8);
      if(fin[0]==pos[0] && fin[1]>pos[1])
      {
        list.removeIf(iarr -> iarr[1]+1<pos[1]);
      }
      else if(fin[0]==pos[0] && fin[1]<pos[1])
      {
        list.removeIf(iarr -> iarr[1]-1>pos[1]);
      }
      else if(fin[0]>pos[0] && fin[1]==pos[1])
      {
        list.removeIf(iarr -> iarr[0]+1<pos[0]);
      }
      else if(fin[0]<pos[0] && fin[1]==pos[1])
      {
        list.removeIf(iarr -> iarr[0]-1>pos[0]);
      }
      else if(fin[0]>pos[0] && fin[1]>pos[1])
      {
        list.removeIf(iarr -> iarr[0]<pos[0]-2/d || iarr[1]<pos[1]-2/d);
      }
      else if(fin[0]>pos[0] && fin[1]<pos[1])
      {
        list.removeIf(iarr -> iarr[0]<pos[0]-2/d || iarr[1]>pos[1]+2/d);
      }
      else if(fin[0]<pos[0] && fin[1]<pos[1])
      {
        list.removeIf(iarr -> iarr[0]>pos[0]+2/d || iarr[1]>pos[1]+2/d);
      }
      else if(fin[0]<pos[0] && fin[1]>pos[1])
      {
        list.removeIf(iarr -> iarr[0]>pos[0]+2/d || iarr[1]<pos[1]-2/d);
      }
      return list;
    }
}