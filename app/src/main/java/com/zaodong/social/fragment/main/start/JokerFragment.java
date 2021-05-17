package com.zaodong.social.fragment.main.start;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.zaodong.social.R;
import com.zaodong.social.activity.start.DetailsActivity;
import com.zaodong.social.adapter.JokerAdapter;
import com.zaodong.social.bean.Jokerbean;
import com.zaodong.social.bean.Yzmfbean;
import com.zaodong.social.model.Sputils;
import com.zaodong.social.presenter.start.IJokerpresenter;
import com.zaodong.social.presenter.start.Jokerpresenter;
import com.zaodong.social.view.Jokerview;
import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class JokerFragment extends Fragment implements Jokerview {
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mJoker_sw;
    private IJokerpresenter jokerpresenter;
    private ArrayList<Jokerbean.DataBean> arrayList=new ArrayList<>();
    private JokerAdapter jokerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_joker, container, false);
        jokerpresenter=new Jokerpresenter(this);
        initview();
        return view;
    }
    private void initview() {
        recyclerView=view.findViewById(R.id.mJoker_recy);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        jokerpresenter.loadDatajoker(Sputils.getInstance().getuser_id(),"1","100");
        mJoker_sw = view.findViewById(R.id.mJoker_sw);
        mJoker_sw.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mJoker_sw.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mJoker_sw.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mJoker_sw.setSize(SwipeRefreshLayout.LARGE);
        //设置下拉刷新的监听
        mJoker_sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jokerpresenter.loadDatajoker(Sputils.getInstance().getuser_id(),"1","100");
                mJoker_sw.setRefreshing(false);
            }
        });
    }
    @Override
        public void showJoker(final Jokerbean jokerbean) {
        arrayList.clear();
        arrayList.addAll(jokerbean.getData());
        jokerAdapter=new JokerAdapter(arrayList,getContext());
        recyclerView.setAdapter(jokerAdapter);
        jokerAdapter.notifyDataSetChanged();
        jokerAdapter.setOnItemClickwang(new JokerAdapter.OnItemClickwang() {
            @Override
            public void OnItemClickwang(View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.DETAIL_ID,jokerbean.getData().get(position).getUser_id()+"");
                startActivity(intent);
            }
        });
    }
    @Override
    public void showJokerf(Yzmfbean yzmfbean) {
        Toast.makeText(getContext(), yzmfbean.getMsg()+"", Toast.LENGTH_SHORT).show();
    }
}
