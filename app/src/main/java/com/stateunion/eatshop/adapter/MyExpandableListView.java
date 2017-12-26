package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stateunion.eatshop.R;

/**
 * Created by villageChief on 2017/12/26.
 */

public class MyExpandableListView extends BaseExpandableListAdapter {
    private String[] groups;
    private String[][] childs;

    private Context context;
   public MyExpandableListView(Context context,String[] groups,String[][] childs){
    this.context=context;
    this.groups=groups;
    this.childs=childs;
   }
    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];

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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group, null);
        }
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group);
        tv_group.setText(groups[groupPosition]);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child, null);
        }

        ImageView iv_child = (ImageView) convertView.findViewById(R.id.iv_child);
        TextView tv_child = (TextView) convertView.findViewById(R.id.tv_child);

        //iv_child.setImageResource(resId);
        tv_child.setText(childs[groupPosition][childPosition]);

        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
