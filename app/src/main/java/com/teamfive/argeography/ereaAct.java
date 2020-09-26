package com.teamfive.argeography;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ereaAct extends AppCompatActivity {


    private ArFragment arFragment;
    private ArrayList<Integer> imagesPath = new ArrayList<Integer>();
    private ArrayList<String> namesPath = new ArrayList<>();
    private ArrayList<String> modelNames = new ArrayList<>();
    AnchorNode anchorNode;
    private Button btnRemove;
    EditText dfg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erea);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        btnRemove = (Button)findViewById(R.id.remove);
        dfg = (EditText)findViewById(R.id.dfg);
        getImages();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                .setSource(this,Uri.parse(Common.model))
                .build()
                .thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable));
            String yo = Common.model.toString();
            dfg.setText(yo);

        });

      //  String yo = Common.model.toString();
        btnRemove.setOnClickListener(view -> removeAnchorNode(anchorNode)
        );
    }

    private void getImages() {
        imagesPath.add(R.drawable.sol);
        imagesPath.add(R.drawable.mercurio);
        imagesPath.add(R.drawable.venus);
        imagesPath.add(R.drawable.mundos);
        imagesPath.add(R.drawable.marte);
        imagesPath.add(R.drawable.jupiter);
        imagesPath.add(R.drawable.saturno);
        imagesPath.add(R.drawable.urano);
        imagesPath.add(R.drawable.neptuno);
        namesPath.add("Sol");
        namesPath.add("Mercurio");
        namesPath.add("Venus");
        namesPath.add("Tierra");
        namesPath.add("Marte");
        namesPath.add("Jupiter");
        namesPath.add("Saturno");
        namesPath.add("Urano");
        namesPath.add("Neptuno");
        modelNames.add("table.sfb");
        modelNames.add("mercurio.sfb");
        modelNames.add("venus.sfb");
        modelNames.add("mundos.sfb");
        modelNames.add("marte.sfb");
        modelNames.add("jupiter.sfb");
        modelNames.add("saturno.sfb");
        modelNames.add("urano.sfb");
        modelNames.add("neptuno.sfb");
        initaiteRecyclerview();
    }

    private void initaiteRecyclerview() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this,namesPath,imagesPath,modelNames);
        recyclerView.setAdapter(adapter);



    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {

        
        anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }

    public void removeAnchorNode(AnchorNode nodeToremove) {
        if (nodeToremove != null) {
            arFragment.getArSceneView().getScene().removeChild(nodeToremove);
            nodeToremove.getAnchor().detach();
            nodeToremove.setParent(null);
            nodeToremove = null;
            //hay qu revisar esta parte
            dfg.setText("");
        }
    }
}
