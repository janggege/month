package shixun.lj.bw.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.mvp.Bean.Show;

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Show.ResultBean resultBean;
    Context context;
    private final int RXXP_TYPE = 0;
    private final int MLSS_TYPE = 1;
    private final int PZSH_TYPE = 2;


    public Myadapter(Show.ResultBean resultBean, Context context) {
        this.resultBean = resultBean;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return RXXP_TYPE;
        } else if (position == 1) {
            return MLSS_TYPE;
        } else if (position == 2) {
            return PZSH_TYPE;
        }
        return position;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == RXXP_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.xin, viewGroup, false);
            RxxpHolder rxxpHolder = new RxxpHolder(view);
            return rxxpHolder;
        } else if (i == MLSS_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.moli, viewGroup, false);
            MlssHolder mlssHolder = new MlssHolder(view);
            return mlssHolder;


        } else if (i == PZSH_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.pzsh, viewGroup, false);
            PzshHolder pzshHolder = new PzshHolder(view);
            return pzshHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof RxxpHolder) {

            RxxpHolder rxxpHolder = (RxxpHolder) viewHolder;
            final List<Show.ResultBean.RxxpBean.CommodityListBean> rxxp = resultBean.getRxxp().getCommodityList();
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //可以不写，因为LinearLayoutManager默认的就是纵向排列方式

            rxxpHolder.recyclerView.setLayoutManager(layoutManager);
            Xadapter xadapter = new Xadapter(rxxp, context);
            xadapter.setXonclick(new Xadapter.xonclick() {
                @Override
                public void onclick(int i) {
                    onclick.onclick(i);
                }
            });
            rxxpHolder.recyclerView.setAdapter(xadapter);


        } else if (viewHolder instanceof MlssHolder) {
            MlssHolder mlssHolder = (MlssHolder) viewHolder;
            List<Show.ResultBean.MlssBean.CommodityListBeanXX> mlss = resultBean.getMlss().getCommodityList();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            mlssHolder.recyclerView.setLayoutManager(linearLayoutManager);
            Moliadapter moliadapter = new Moliadapter(mlss, context);
            moliadapter.setMonclick(new Moliadapter.monclick() {
                @Override
                public void mclick(int id) {
                    onclick1.onclick1(id);
                }
            });
            mlssHolder.recyclerView.setAdapter(moliadapter);


        } else if (viewHolder instanceof PzshHolder) {
            PzshHolder pzshHolder = (PzshHolder) viewHolder;
            List<Show.ResultBean.PzshBean.CommodityListBeanX> pzsh = resultBean.getPzsh().getCommodityList();
            pzshHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            Pzshadapter pzshadapter = new Pzshadapter(pzsh, context);
            pzshadapter.setPonclick(new Pzshadapter.ponclick() {
                @Override
                public void ponclick(int id) {
                    onclick2.onclick2(id);
                }
            });
            pzshHolder.recyclerView.setAdapter(pzshadapter);

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    //热销新品
    public class RxxpHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;


        public RxxpHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.xrecy);
        }
    }

    //品质生活
    public class PzshHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;


        public PzshHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.precy);
        }
    }

    //魔丽时尚
    public class MlssHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;


        public MlssHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.mrecy);
        }
    }

    public interface onclick {
        void onclick(int id);
    }

    onclick onclick;

    public void setOnclick(Myadapter.onclick onclick) {
        this.onclick = onclick;
    }

    public interface onclick1 {
        void onclick1(int id);
    }

    onclick1 onclick1;

    public void setOnclick(Myadapter.onclick1 onclick1) {
        this.onclick1 = onclick1;
    }


    public interface onclick2 {
        void onclick2(int id);
    }

    onclick2 onclick2;

    public void setOnclick(Myadapter.onclick2 onclick2) {
        this.onclick2 = onclick2;
    }
}
