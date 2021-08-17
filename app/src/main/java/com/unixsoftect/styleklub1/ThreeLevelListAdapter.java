package com.unixsoftect.styleklub1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

// old code

//public class ThreeLevelListAdapter extends BaseExpandableListAdapter {
//
//    List<levelmodel> parentHeaders;
//    List<List<levelmodel>> secondLevel;
//    private Context context;
//    List<LinkedHashMap<String, List<levelmodel>>> data;
//
//    public ThreeLevelListAdapter(Context context, List<levelmodel> parentHeader, List<List<levelmodel>> secondLevel, List<LinkedHashMap<String, List<levelmodel>>> data) {
//        this.context = context;
//        this.parentHeaders = parentHeader;
//        this.secondLevel = secondLevel;
//        this.data = data;
//    }
//
//    @Override
//    public int getGroupCount() {
//        return parentHeaders.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        // no idea why this code is working
//        return 1;
//
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//
//        return groupPosition;
//    }
//
//    @Override
//    public Object getChild(int group, int child) {
//        return child;
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        //Main Menu
//        convertView = inflater.inflate(R.layout.list_group_inner, null);
////        ImageView arrow3 = (ImageView) convertView.findViewById(R.id.arrow9);
//        ImageView icon = (ImageView) convertView.findViewById(R.id.image9);
////         if (groupPosition>0)
////        {
////        if (isExpanded)
////        {
////            arrow3.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_keyboard_arrow_up_24));
////        }
////        else
////        {
////            arrow3.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_keyboard_arrow_down_24));
////        }
////        }
//        TextView text = (TextView) convertView.findViewById(R.id.childtext);
//        levelmodel levelmodel=parentHeaders.get(groupPosition);
//        text.setText(levelmodel.getTitle());
//        Log.e("TAG", "getGroupView: "+levelmodel.getTitle());
//        icon.setImageResource(parentHeaders.get(groupPosition).getIcon());
////        if (parentHeaders.isEmpty())
////        {
////            Log.e("menu text","itsempty");
////        }
////        else
////        {
////            Log.e("menu text",parentHeaders.get(groupPosition).getTitle());
////            Log.e("menu text",""+parentHeaders.size());
////            Log.e("clicked position", parentHeaders.get(groupPosition).getTitle());
////        }
//
//        return convertView;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);
//        List<levelmodel> headers = secondLevel.get(groupPosition);
//        if(headers.size()>0){
//            List<List<levelmodel>> childData = new ArrayList<>();
//            LinkedHashMap<String, List<levelmodel>> secondLevelData = data.get(groupPosition-1);
//            for(String key : secondLevelData.keySet())
//            {
//                childData.add(secondLevelData.get(key));
//            }
//
//            secondLevelELV.setAdapter(new SecondLevelAdapter(context, headers,childData));
//            secondLevelELV.setGroupIndicator(null);
//            secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//                int previousGroup = -1;
//                @Override
//                public void onGroupExpand(int groupPosition) {
//                    if(groupPosition != previousGroup)
//                        secondLevelELV.collapseGroup(previousGroup);
//                    previousGroup = groupPosition;
//                }
//            });
//
//        }
//        return secondLevelELV;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//}


//oldcode

//github

public class ThreeLevelListAdapter extends BaseExpandableListAdapter {

    List<levelmodel> parentHeaders;
    List<List<levelmodel>> secondLevel;
    List<LinkedHashMap<String, List<levelmodel>>> data;
    private final Context context;

    public ThreeLevelListAdapter(Context context, List<levelmodel> parentHeader, List<List<levelmodel>> secondLevel, List<LinkedHashMap<String, List<levelmodel>>> data) {
        this.context = context;

        this.parentHeaders = parentHeader;

        this.secondLevel = secondLevel;

        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return parentHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // no idea why this code is working
        return 1;

    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupPosition;
    }

    @Override
    public Object getChild(int group, int child) {
        return child;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group_inner, null);
        TextView text = convertView.findViewById(R.id.childtext);
        ImageView icon = convertView.findViewById(R.id.image9);
        ImageView arrow = convertView.findViewById(R.id.arrow9);
        text.setText(parentHeaders.get(groupPosition).getTitle());
        icon.setImageResource(parentHeaders.get(groupPosition).getIcon());
        if (parentHeaders.get(groupPosition).haschild) {
            if (isExpanded) {
                arrow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_down_24));
            } else {
                arrow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_up_24));
            }
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);
        //categoties headers
        List<levelmodel> headers = secondLevel.get(groupPosition);
        List<List<levelmodel>> childData = new ArrayList<>();
        HashMap<String, List<levelmodel>> secondLevelData = data.get(groupPosition);
        //extracting values from keys
        for (String key : secondLevelData.keySet()) {
            childData.add(secondLevelData.get(key));
        }
        secondLevelELV.setAdapter(new SecondLevelAdapter(context, headers, childData));
        secondLevelELV.setGroupIndicator(null);
        secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    secondLevelELV.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        return secondLevelELV;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
//github