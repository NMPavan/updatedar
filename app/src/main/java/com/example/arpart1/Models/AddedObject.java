package com.example.arpart1.Models;

import com.google.ar.sceneform.AnchorNode;

public class AddedObject
{

    private  AnchorNode anchorNode;
    private  int i=0;

    public AddedObject(AnchorNode anchorNode, int i) {

        this.anchorNode = anchorNode;
        this.i = i;
    }

    public AnchorNode getAnchorNode() {
        return anchorNode;
    }

    public int getI() {
        return i;
    }
}
