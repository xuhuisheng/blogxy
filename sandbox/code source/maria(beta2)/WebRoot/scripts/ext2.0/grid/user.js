Application.user = {
    init : function() {
	    Application.tree.on('click', function(node, e){
	         if(node.id == 'user'){
		    	var tab = Application.tabs.getItem('user');
		    	
	    		if(tab != null){
	    			Application.tabs.setActiveTab(tab);
	    		}else{
					// data source
					var path = Ext.get('path').dom.getAttribute('action');
						
					var xg = Ext.grid;
					
				    // shared reader
				    var reader = new Ext.data.JsonReader({
				    	root: 'rows',
				    	totalProperty: 'totalCount',
				    	id:"id"
				    }, [
				       {name: 'name', mapping:'name'},// "mapping" property not needed if it's the same as "name"
				       {name: 'password'}// this field will use "occuption" as the mapping
				    ]);
				    
				    // grid data
				    var ds = new Ext.data.Store({
				       	// create grid
				           reader: reader,
				           // load data
				           proxy: new Ext.data.HttpProxy({url: path + '/user/grid.do'})
				           // sort
				           //sortInfo:{field:'name',direction:'desc'}
					});
					
					// trigger the data store load
					ds.load({params:{start:0, limit:2}});
					
					// data sort
					//ds.setDefaultSort('name', 'desc');
					
					// column define
					var cm = new xg.ColumnModel([
						new xg.RowNumberer(),
				        {header: "Name", width: 20, sortable: true, dataIndex: 'name'},
				        {header: "Password", width: 20, sortable: true, dataIndex: 'password'},
				        new xg.CheckboxSelectionModel()
					]);
					
				    // by default columns are sortable
				    cm.defaultSortable = true;
				    
				    // user grid
				    var user = new xg.GridPanel({
				    	//el:'user-grid',
				        ds: ds,
				        cm: cm,
				        sm: new xg.CheckboxSelectionModel(),
				        border:false,
				        trackMouseOver:false,
				        viewConfig: {
				            forceFit:true,
				            enableRowBody:true,
				            showPreview:true
				        },			        
				        //title:'User Grid',
				        //iconCls:'icon-grid',
				        margins:'0 5 5 5',
				        width:100,
				        // turn on remote sorting
				        remoteSort: true,
				        loadMask: true,
				        closable:true,
				        autoScroll:true
				    });
				    
				    tab = new Ext.Panel({
				    	id:'user',
				    	title:'User Grid',
				    	iconCls:'user-grid',
				    	layout:'fit',
				    	closable:true,
				    	width:100,
				    	items:user,
				        // inline toolbars
				        tbar:[{
				            text:'Add Something',
				            tooltip:'Add a new row',
				            iconCls:'add'
				        }, '-', {
				            text:'Edit Something',
				            tooltip:'Edit the selected row',
				            iconCls:'edit'
				        },'-',{
				            text:'Remove Something',
				            tooltip:'Remove the selected item',
				            iconCls:'remove'
				        }],
				        bbar: new Ext.PagingToolbar({
				            pageSize: 2,
				            store: ds,
				            displayInfo: true,
				            displayMsg: 'Displaying rows {0} - {1} of {2}',
				            emptyMsg: "No topics to display"
				        })	    	
				    });
				    
				    Application.tabs.add(tab);
				    
				    //Application.tabs.add(user);
				    
				    Application.tabs.setActiveTab(tab);		    		
	    		}
	         }
	    });    	
    }
};

Ext.onReady(Application.user.init);