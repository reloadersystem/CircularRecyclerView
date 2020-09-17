package reloader.circlelayoutmanager;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import reloader.circlelayoutmanager.BottomRecycler.RecyclerAdapterBottom;
import reloader.circlelayoutmanager.BottomRecycler.mIconBottom;

public class MainActivity extends AppCompatActivity {
    private List<mIconModel> listIcon;
    private List<mIconBottom> lisIconBottom;
    RecyclerAdapterCircle recyclerAdapterCircle;
    RecyclerView recyclerView, recyclerBottom;
    LinearLayout ln_contenedor;
    RecyclerAdapterBottom recyclerAdapterBottom;
    ImageView img_central;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listIcon = new ArrayList<>();
        lisIconBottom = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        ln_contenedor = (LinearLayout) findViewById(R.id.ln_contenedor);
        recyclerBottom = (RecyclerView) findViewById(R.id.recyclerBottom);

        //int myColor = Color.parseColor("#ff00ff");
        //swipeRefreshLayout.setProgressBackgroundColorSchemeColor(myColor);


//        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
//        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
//        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorIconAlter);

        loadRecyclerView();

        loadRecyclerBottom();

        img_central = (ImageView) findViewById(R.id.img_central);

        //final ScrollZoomLayoutManager scrollZoomLayoutManager = new ScrollZoomLayoutManager(this,Dp2px(10));
        //recyclerView.addOnScrollListener(new CenterScrollListener());

        img_central.setImageResource(R.drawable.image1);

        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;


        //FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isCircle){
//                    recyclerView.setLayoutManager(scrollZoomLayoutManager);
//                }else{
//                    recyclerView.setLayoutManager(circleLayoutManager);
//                }
//                isCircle = !isCircle;
//            }
//        });
    }

    private void loadRecyclerBottom() {

        lisIconBottom.add(new mIconBottom(R.drawable.panelprincipal7, "Mundo"));
        lisIconBottom.add(new mIconBottom(R.drawable.panelprincipal8, "Conocimiento"));
        lisIconBottom.add(new mIconBottom(R.drawable.panelprincipal9, "Video"));
        lisIconBottom.add(new mIconBottom(R.drawable.panelprincipal10, "Diapositivas"));

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerAdapterBottom = new RecyclerAdapterBottom(this, lisIconBottom);
        recyclerBottom.setLayoutManager(layoutManager);
        recyclerBottom.setAdapter(recyclerAdapterBottom);
    }


    private void loadRecyclerView() {

        listAddIcon();
        //listAddIconSocial();

        recyclerAdapterCircle = new RecyclerAdapterCircle(listIcon, this);
        final CircleLayoutManager circleLayoutManager = new CircleLayoutManager(this);
        recyclerView.addOnScrollListener(new CenterScrollListener());
        recyclerView.setLayoutManager(circleLayoutManager);
        recyclerView.setAdapter(recyclerAdapterCircle);
    }

    private void listAddIcon() {
        listIcon.add(new mIconModel(R.drawable.image1, "Facebook"));
        listIcon.add(new mIconModel(R.drawable.image2, "Twiter"));
        listIcon.add(new mIconModel(R.drawable.image3, "Pinterest"));
        listIcon.add(new mIconModel(R.drawable.image4, "Skype"));
        listIcon.add(new mIconModel(R.drawable.image5, "Android"));
        listIcon.add(new mIconModel(R.drawable.image6, "Google"));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        //| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
