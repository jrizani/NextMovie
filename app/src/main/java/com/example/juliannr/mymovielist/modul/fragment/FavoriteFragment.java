package com.example.juliannr.mymovielist.modul.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.juliannr.mymovielist.R;
import com.example.juliannr.mymovielist.model.MovieDetail;
import com.example.juliannr.mymovielist.modul.adapter.MyFavoriteRecyclerViewAdapter;
import com.example.juliannr.mymovielist.modul.presenter.FavoritePresenter;
import com.example.juliannr.mymovielist.modul.view.FavoriteView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FavoriteFragment extends Fragment implements FavoriteView {
    private FavoritePresenter presenter;
    private Context context;
    private OnListFragmentInteractionListener mListener;
    @BindView(R.id.no_favorite)
    RelativeLayout noFavorite;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FavoriteFragment() {
    }

    @SuppressWarnings("unused")
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadFavorite();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        ButterKnife.bind(this, view);
        presenter = new FavoritePresenter();
        presenter.setView(this);
        context = getContext();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MovieDetail item);
    }

    @Override
    public void onDataFound(List<MovieDetail> movies) {
        noFavorite.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(new MyFavoriteRecyclerViewAdapter(movies, mListener, context));
    }

    @Override
    public void onNoFavorite() {
        noFavorite.setVisibility(View.VISIBLE);
    }
}
