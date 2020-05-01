package jrc.engine.map;

import jrc.engine.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter @Setter @AllArgsConstructor
public class CellSharedData {
    
    public static final CellSharedData EMPTY = new CellSharedData(Color.NONE);
    
    private final Color color;
}