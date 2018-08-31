package com.ztj.livetv.util;

import com.ztj.livetv.db.entity.GameTypeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

public class AlgorithmUtil {

    /**
     * 求前k大个数组成的数组(堆排实现)
     */
    private static void swap(GameTypeInfo[] arr, int a, int b){
        GameTypeInfo tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static void adjust(GameTypeInfo[] arr,int i,int length){
        GameTypeInfo tmp = arr[i];
        for(int k=2*i+1;k<length;k=2*k+1){
            if(k+1<length && arr[k].getSelectCount()<arr[k+1].getSelectCount()){
                k++;
            }
            if(arr[k].getSelectCount()>tmp.getSelectCount()){
                arr[i] = arr[k];
                i=k;
            }else{
                break;
            }
        }
        arr[i] = tmp;
    }

    public static List<GameTypeInfo> getKthofGameTypeInfo(GameTypeInfo[] arr, int k){
        List<GameTypeInfo> result = new ArrayList<>();
        for(int i=arr.length/2-1;i>=0;i--){
            adjust(arr,i,arr.length);
        }
        for(int j=arr.length-1;j>=0&&k>0;j--){
            swap(arr,0,j);
            result.add(arr[j]);
            k--;
            adjust(arr,0,j);
        }
        return result;
    }

    public static List<GameTypeInfo> getKthofGameTypeInfo(List<GameTypeInfo> list,int k){
        if(list==null || list.size()==0)return null;
        GameTypeInfo[] arr = list.toArray(new GameTypeInfo[list.size()]);
        return getKthofGameTypeInfo(arr,k);
    }
}
