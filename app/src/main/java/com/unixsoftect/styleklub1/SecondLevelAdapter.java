package com.unixsoftect.styleklub1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//oldcode

//public class SecondLevelAdapter extends BaseExpandableListAdapter {
//
//    private Context context;
//
//
//    List<List<levelmodel>> data;
//
//    List<levelmodel> headers;
//    public SecondLevelAdapter(Context context, List<levelmodel> headers, List<List<levelmodel>> data) {
//        this.context = context;
//        this.data = data;
//        this.headers = headers;
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return headers.get(groupPosition);
//    }
//
//    @Override
//    public int getGroupCount() {
//        return headers.size();
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.list_group_child, null);
//        ImageView arrowchild = (ImageView) convertView.findViewById(R.id.arrow2);
//        if (isExpanded)
//        {
//            arrowchild.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_keyboard_arrow_up_24));
//        }
//        else
//        {
//            arrowchild.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_keyboard_arrow_down_24));
//        }
//        TextView text = (TextView) convertView.findViewById(R.id.Header_child);
//        levelmodel levelmodel= (com.unixsoftect.styleklub1.levelmodel) getGroup(groupPosition);
//        String groupText = levelmodel.getTitle();
//        text.setText(groupText);
//        return convertView;
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        List<levelmodel> childData= data.get(groupPosition);
//        return childData.get(childPosition);
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.list_group_header, null);
//        ImageView arrow = (ImageView) convertView.findViewById(R.id.arrow);
//        TextView textView = (TextView) convertView.findViewById(R.id.Header_item);
//        List<levelmodel> childArray=data.get(groupPosition);
//        String text = childArray.get(childPosition).getTitle();
//        textView.setText(text);
//        return convertView;
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//
//
//      List<levelmodel> children = data.get(groupPosition);
//
//
//        return Math.max(children.size(), 0);
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//}
//oldcode

//github
public class SecondLevelAdapter extends BaseExpandableListAdapter {
    List<List<levelmodel>> data;
    List<levelmodel> headers;
    private final Context context;

    public SecondLevelAdapter(Context context, List<levelmodel> headers, List<List<levelmodel>> data) {
        // header listdata
        // data extracted from key
        this.context = context;
        this.data = data;
        this.headers = headers;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group_child, null);
        TextView text = convertView.findViewById(R.id.Header_child);
        ImageView icon = convertView.findViewById(R.id.image);
        levelmodel levelmodel = (com.unixsoftect.styleklub1.levelmodel) getGroup(groupPosition);
        String groupText = levelmodel.getTitle();
        icon.setImageResource(levelmodel.getIcon());
        text.setText(groupText);
        return convertView;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition){
        List<levelmodel> childData;
        //multiple lists inside levelmodel
        childData = data.get(groupPosition);
        return childData.get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_group_header, null);
        TextView textView = convertView.findViewById(R.id.Header_item);
        ImageView icon = convertView.findViewById(R.id.image);
        List<levelmodel> childArray = data.get(groupPosition);
        String text = childArray.get(childPosition).getTitle();
        icon.setImageResource(childArray.get(childPosition).getIcon());
        textView.setText(text);
        return convertView;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        //multiple lists inside list model
        //to get childlist from list model
        List<levelmodel> children = data.get(groupPosition);
        return children.size();
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
//github