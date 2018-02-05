package com.stateunion.eatshop.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stateunion.eatshop.R;

import java.util.HashMap;

/**
 * Created by admini on 2018/1/9.
 */

public class NormalExpandableListAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "NormalExpandableListAda";
    private String[] groupData;
    private String[][] childData;
    private String[][] shijianData;

    private OnGroupExpandedListener mOnGroupExpandedListener;
    Context context;
     // 判断是否点击的标识
     SparseBooleanArray selected;
    int old = -1;
    int parentPosition = -1;
    public NormalExpandableListAdapter(String[] groupData, String[][] childData,String[][] cshijianData, Context context) {
        this.groupData = groupData;
        this.childData = childData;
        selected = new SparseBooleanArray();
        this.context=context;
        this.shijianData=cshijianData;
    }

    public void setOnGroupExpandedListener(OnGroupExpandedListener onGroupExpandedListener) {
        mOnGroupExpandedListener = onGroupExpandedListener;
    }

    @Override
    public int getGroupCount() {
        return groupData.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData[groupPosition][childPosition];
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
    public View getGroupView(int groupPosition, boolean isExpanded, View
            convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_group_indicator, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label_group_indicator);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(groupData[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View
            convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_child, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label_expand_child);
            childViewHolder.shijian= (TextView) convertView.findViewById(R.id.shijian);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
//        if (isLastChild) {
//            // 设置展开后的group标头图片
//            childViewHolder.tvTitle.setBackgroundResource(R.color.investinfo_color);
//            // 在折叠后再次展开，之前的选中会消失，此处需要设置一次
//            if (groupPosition == groupPosition) {
//                setItemChecked(mGroupPosition, mChildPosition);
//            }
//        } else {
//            // 折叠后的group标头图片
//            indicator
//                    .setImageResource(R.drawable.expander_arrow_collapse);
//        }

//重点代码
        if (selected.get(childPosition)&&this.parentPosition==groupPosition) {
            childViewHolder.tvTitle.setBackgroundResource(R.color.beijing);
            childViewHolder.tvTitle.setTextColor (context.getResources().getColor(R.color.white));
            childViewHolder.shijian.setBackgroundResource(R.color.beijing);
            childViewHolder.shijian.setTextColor (context.getResources().getColor(R.color.white));
        } else {
            // convertView.setBackgroundResource(R.color.white);
            childViewHolder.tvTitle.setBackgroundResource(R.color.white);
            childViewHolder.tvTitle.setTextColor (context.getResources().getColor(R.color.gray));
            childViewHolder.shijian.setBackgroundResource(R.color.white);
            childViewHolder.shijian.setTextColor (context.getResources().getColor(R.color.gray));
        }

        childViewHolder.tvTitle.setText(childData[groupPosition][childPosition]);
        childViewHolder.shijian.setText(shijianData[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        Log.d(TAG, "onGroupExpanded() called with: groupPosition = [" + groupPosition + "]");
        if (mOnGroupExpandedListener != null) {
            mOnGroupExpandedListener.onGroupExpanded(groupPosition);
        }
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        Log.d(TAG, "onGroupCollapsed() called with: groupPosition = [" + groupPosition + "]");
    }

    private static class GroupViewHolder {
        TextView tvTitle;
    }

    private static class ChildViewHolder {
        TextView tvTitle,shijian;
    }
    public void setSelectedItem(int groupPosition,int selected) {
        this.parentPosition = groupPosition;
        if (old != -1) {
            this.selected.put(old, false);
        }
        this.selected.put(selected, true);
        old = selected;
    }
}
