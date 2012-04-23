package com.med8.augmentedreality.data;

import java.util.List;

import com.med8.augmentedreality.ui.Marker;


/**
 * This abstract class should be extended for new data sources.
 * 
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public abstract class DataSource {
    public abstract List<Marker> getMarkers();
}
