package com.example.user.scooltracker.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.scooltracker.R;
import com.example.user.scooltracker.Teacher.Teach_sentBoxActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 07-02-2018.
 */

public class TSB_adapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;

    public TSB_adapter(Teach_sentBoxActivity teach_sentBoxActivity, List<String> listDataHeader,
                       HashMap<String, List<String>> listDataChild, List<String> listDataSubHeader) {
        this._context=teach_sentBoxActivity;
        this._listDataHeader=listDataHeader;
        this._listDataChild=listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle=(String)getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)this._context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_group,null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.list_title);
        ImageView  expandnColl=convertView.findViewById(R.id.expandCollapse);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        String headerTitle=(String)getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)this._context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_child,null);

        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.list_child);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
