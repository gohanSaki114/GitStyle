package com.unixsoftect.styleklub1;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    List<headermodal> listDataHeader;
    HashMap<headermodal, List<headermodal>> listChildData;
    HashMap<String,List<String>> mlistData_secondLevel_Map;
    Context context;

    public ExpandableListAdapter(Context context, List<headermodal> listDataHeader, HashMap<headermodal, List<headermodal>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listChildData = listChildData;
        String mdataheader;
        mlistData_secondLevel_Map = new HashMap<>();
        int parentcount = listDataHeader.size();
        for (int i = 0; i<parentcount; i++)
        {
            String content = listDataHeader.get(i).toString();
            switch (content)
            {

            }
        }
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.listChildData.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return Objects.requireNonNull(this.listChildData.get(this.listDataHeader.get(groupPosition))).size();
    }

    @Override
    public headermodal getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public headermodal getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(this.listChildData.get(this.listDataHeader.get(groupPosition)))
                .get(childPosition);
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
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).menuName;
        LayoutInflater infalInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (infalInflater != null)
            convertView = infalInflater.inflate(R.layout.list_group_header, parent, false);
        TextView lblListHeader = convertView.findViewById(R.id.Header_item);
        ImageView img = convertView.findViewById(R.id.arrow);
        ImageView icon = convertView.findViewById(R.id.image);
        if (getGroup(groupPosition).isGroup) {
            if (isExpanded) {
                img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_up_24));
            } else
                img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_down_24));
        }
        //lblListHeader.setTypeface(context.getResources().getFont(R.font.courgette_regular),Typeface.NORMAL);
        if (getGroup(groupPosition).icon != null)
            icon.setImageDrawable(getGroup(groupPosition).icon);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = getChild(groupPosition, childPosition).menuName;
        LayoutInflater infalInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (infalInflater != null) {
            convertView = infalInflater.inflate(R.layout.list_group_child, parent, false);
        }
        ImageView icon = convertView.findViewById(R.id.image);
        TextView txtListChild = convertView.findViewById(R.id.Header_child);
        icon.setImageDrawable(getChild(groupPosition, childPosition).icon);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
