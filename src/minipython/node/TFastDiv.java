/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import minipython.analysis.*;

public final class TFastDiv extends Token
{
    public TFastDiv()
    {
        super.setText("/=");
    }

    public TFastDiv(int line, int pos)
    {
        super.setText("/=");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TFastDiv(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTFastDiv(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TFastDiv text.");
    }
}