package com.kapaali.nestedrv

class NestedData {
    var title : String = ""
    val nestedData = mutableListOf<NestedData>()

    /** boolean to maintain the expanded or collapsed state of item in recycler-view to avoid false state
     * when views are reused when list is scrolled **/
    var isExpanded = false
}