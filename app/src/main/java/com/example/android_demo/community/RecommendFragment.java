package com.example.android_demo.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_demo.R;
import com.example.android_demo.bean.CommunityBean;
import com.example.android_demo.databinding.FragmentRecommendBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SummCoder
 * @date 2023/12/7 20:39
 */
public class RecommendFragment extends Fragment {
    private FragmentRecommendBinding binding;

    List<CommunityBean> communityBeanList = new ArrayList<>();

    MyAdapter myAdapter;

    private static int[] coverArray = {R.drawable.cover0, R.drawable.cover1, R.drawable.cover2};
    private static String[] nameArray = {
            "薅羊毛小分队",
            "今日热点",
            "华为鸿蒙",
            "手机摄影",
            "沙雕乐园",
            "开箱评测"
    };
    private static String[] followArray = {
            "33.7万关注",
            "4.2万关注",
            "5.8万关注",
            "11.3万关注",
            "8.7万关注",
            "7.2万关注"
    };
    private RecyclerView recyclerview_recommend;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecommendBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        recyclerview_recommend = root.findViewById(R.id.recyclerview_recommend);
        for(int i = 0; i < 12; i++){
            CommunityBean communityBean = new CommunityBean(i, coverArray[i%3], nameArray[i%6], followArray[i%6]);
            communityBeanList.add(communityBean);
        }
        myAdapter = new MyAdapter();
        recyclerview_recommend.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview_recommend.setLayoutManager(layoutManager);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    class MyAdapter extends RecyclerView.Adapter<RecommendFragment.MyViewHolder>{

        @NonNull
        @Override
        public RecommendFragment.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(getActivity(), R.layout.community_item, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecommendFragment.MyViewHolder holder, int position) {
            CommunityBean communityBean = communityBeanList.get(position);
            holder.iv_cover.setImageResource(communityBean.cover);
            holder.tv_community_name.setText(communityBean.name);
            holder.tv_community_follow.setText(communityBean.follow);
            holder.cb_community_follow.setText("关注");
        }

        @Override
        public int getItemCount() {
            return communityBeanList.size();
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_cover;
        TextView tv_community_name;

        TextView tv_community_follow;

        CheckBox cb_community_follow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cover = itemView.findViewById(R.id.iv_cover);
            tv_community_name = itemView.findViewById(R.id.tv_community_name);
            tv_community_follow = itemView.findViewById(R.id.tv_community_follow);
            cb_community_follow = itemView.findViewById(R.id.cb_community_follow);
        }
    }
}
