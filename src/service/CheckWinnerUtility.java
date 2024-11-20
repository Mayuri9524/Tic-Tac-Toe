package service;

import exception.GameDrawException;
import model.Board;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckWinnerUtility {
    //2*n+3 HashMap's Needed
    private List<HashMap<Character,Integer>> rowHashMap;
    private List<HashMap<Character,Integer>> colHashMap;
    private HashMap<Character,Integer> toprightDiagonalHashMap;
    private HashMap<Character,Integer> topLeftDiagonalHashMap;
    private HashMap<Character,Integer> cornorHashMap;
    private int size;


    public CheckWinnerUtility(int size) {
        this.size=size;
        rowHashMap=new ArrayList<>();
        colHashMap=new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            rowHashMap.add(new HashMap<>());
            colHashMap.add(new HashMap<>());
        }
        topLeftDiagonalHashMap=new HashMap<>();
        toprightDiagonalHashMap=new HashMap<>();
        cornorHashMap=new HashMap<>();
    }


    public Player checkwinner(Board board, Move curentMove)
    {
        //update row and column hashmap
        char symbol= curentMove.getPlayer().getSymbol();
        int row=curentMove.getCell().getRow();
        int col=curentMove.getCell().getCol();

        HashMap<Character,Integer> rowHash= rowHashMap.get(curentMove.getCell().getRow());
        HashMap<Character,Integer> colHash= colHashMap.get(curentMove.getCell().getCol());
        // can be reduced below using lambda and getOrDefault
        rowHash.put(symbol,rowHash.getOrDefault(symbol,0)+1);
        colHash.put(symbol,colHash.getOrDefault(symbol,0)+1);

/*       if(rowHash.containsKey(symbol))
       {
            rowHash.put(symbol,rowHash.get(symbol)+1);
       }
       else {
           rowHash.put(symbol,1);

    }
*/
        // To update diagonal hashmaps
        if(row==col)
        {topLeftDiagonalHashMap.put(symbol,topLeftDiagonalHashMap.getOrDefault(symbol,0)+1); }

        if((col+row)==(size-1))
        {toprightDiagonalHashMap.put(symbol,toprightDiagonalHashMap.getOrDefault(symbol,0)+1);}

        // For cornor cases
        if((row==0 || row==size-1 ) && (col==0 || col==size-1))
        {cornorHashMap.put(symbol,cornorHashMap.getOrDefault(symbol,0)+1);}

        //Check in all hashMaps is winner present or not
        if(cornorHashMap.getOrDefault(symbol,0)==4 || toprightDiagonalHashMap.getOrDefault(symbol,0)==size ||
                topLeftDiagonalHashMap.getOrDefault(symbol,0)==size || rowHash.get(symbol)==size || colHash.get(symbol)==size)
        {return curentMove.getPlayer();}
        // To Do maintain count and check this without checkdraw function
        if(checkDraw())
        {
            throw  new GameDrawException(" No more winner possible as game has been drawn");
        }
        return null;
    }

    public boolean checkDraw()
    {   for(HashMap<Character,Integer> map: rowHashMap)
        {if(map.size()<=1) {return false;}}

        for(HashMap<Character,Integer> map: colHashMap)
        {if(map.size()<=1) {return false;}}

        if(topLeftDiagonalHashMap.size()<=1 || toprightDiagonalHashMap.size()<=1 || colHashMap.size()<=1)
        {return false;}
        return true;
    }

}
