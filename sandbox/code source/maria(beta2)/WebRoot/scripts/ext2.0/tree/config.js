Application.config = {
    init : function() {
	    Application.tree.on('click', function(node, e){
	         if(node.id == 'config'){
		    	var tab = Application.tabs.getItem('config');
		    	
	    		if(tab != null){
	    			Application.tabs.setActiveTab(tab);
	    		}else{
					// data source
					var path = Ext.get('path').dom.getAttribute('action');
					
				    var xt = Ext.tree;
				    
			        // yui-ext tree
			        var usertree = new xt.TreePanel({
			            //el:'usertree',
			            animate:true, 
			            autoScroll:true,
			            loader: new xt.TreeLoader({dataUrl:path+'/user/tree.do'}),
			            enableDD:true,
			            containerScroll: true,
			 			border:false,
			 			region:'west',
			 			width:350,                
			            dropConfig: {appendOnly:true}
			        });
			        
			        // add a tree sorter in folder mode
			        new xt.TreeSorter(usertree, {folderSort:true});
			        
			        // set the root node
			        var userroot = new xt.AsyncTreeNode({
			            text: 'User', 
			            draggable:false, // disable root node dragging
			            id:'source',
			            allowDrop:false,
			            allowDrag:false            
			        });
			        
			        usertree.setRootNode(userroot);
			        
			        // render the tree
			        //usertree.render();
			        
			        //userroot.expand(false, /*no anim*/ false);
			        
			        /**
			         * 避免node被删除.
			         */
				    usertree.on('remove',function(tree, parent, node){
						var usernode = new xt.TreeNode({
						       text: node.text,
						       id:node.id,
						       allowDrag:true,// 设置拖拽
						       allowDrop:false,// 设置插入
						       cls:'file'
						 });
						 parent.appendChild(usernode);
				    });        
				    
				    
			        // YUI tree            
			        var roletree = new xt.TreePanel({
			            //el:'roletree',
			            animate:true,
			            autoScroll:true,
			            //rootVisible: false,
			            loader: new xt.TreeLoader({
			                dataUrl:path+'/role/tree.do',
			                baseParams: {lib:'yui'} // custom http params
			            }),
			            containerScroll: true,
			 			region:'center',
			 			width:400,              
			            enableDD:true,
			            border:false,
			            dropConfig: {appendOnly:true}
			        });
			        
			        // add a tree sorter in folder mode
			        new xt.TreeSorter(roletree, {folderSort:true});
			        
			        // add the root node
			        var roleroot = new xt.AsyncTreeNode({
			            text: 'Role', 
			            draggable:false, 
			            id:'yui',
			            allowDrop:false,
			            allowDrag:false               
			        });
			        
			        roletree.setRootNode(roleroot);
			        
			        //roletree.render();
			        
			        //roleroot.expand(false, /*no anim*/ false);
			        
				    /**
				     * 判断node是否已存在.
				     */
				    roletree.on('beforenodedrop',function(dropEvent){
				    	//alert(dropEvent.target.text);
				    	//alert(dropEvent.dropNode.text);
				    	//alert(dropEvent.target.findChild('text',dropEvent.dropNode.text));
				    	
				    	if(dropEvent.target.findChild('text',dropEvent.dropNode.text)!= null)
				    		return false;
				    	else
				    		return true;
				    });              
				    	        
				    tab = new Ext.Panel({
				    	id:'config',
				    	title:'Configure',
				    	iconCls:'icon-tree',
				    	layout:'border',
				    	closable:true,
				    	width:100,
				    	items:[usertree,roletree]	    	
				    });
				    
				    Application.tabs.add(tab);
				    
				    Application.tabs.setActiveTab(tab);
	    		}
	    	}
	    });
    }
};

Ext.onReady(Application.config.init);