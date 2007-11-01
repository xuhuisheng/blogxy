Application.layout = {
    init : function() {
    	// 把背景需要的位置撑开,正常显示背景
    	Ext.BLANK_IMAGE_URL = 'images/ext2.0/default/s.gif';
    
    	var xt = Ext.tree;
		
	    // tree root
	    var root = new xt.TreeNode({
	    	text:'Manager',
	    	id:'root',
	    	cls:'folder',
	    	allowDrag:false,
	    	allowDrop:false,
	    	expanded:true
	    });
	    
	    // tree node
	    root.appendChild(
	    	new Ext.tree.TreeNode({id:'user',text:'User',cls:'file',allowDrag:false}),
	    	new Ext.tree.TreeNode({id:'role',text:'Role',cls:'file',allowDrag:false}),
	    	new Ext.tree.TreeNode({id:'config',text:'Config',cls:'file',allowDrag:false})
	    );
	    
	    // tree
	    Application.tree = new xt.TreePanel({
	    	id:'api-tree',// 根据id设置 tree node 选中样式
   			region:'west',
   			//contentEl:'west-div',
   			title:'System Tree',
   			split:true,
   			width:200,
   			minSize:175,
   			maxSize:400,
   			collapsible:true,
   			margins:'0 0 0 5',
   			border:true,
   			root:root
	    });
	    
	    //Application.tree.expandPath('/root');
	    
		Application.tabs = new Ext.TabPanel({
			region:'center',
			margins:'0 5 0 0',//页边空白设置
			deferredRender:false,// 是否延缓面板加载
			activeTab:0,// 设置当前tab页为第一页,不设置页面内容为空白
			resizeTabs:true,
			minTabWidth:135,
			tabWidth:135,
			enableTabScroll:true,
			hideParent:true,
			//collapsible:true,
			//html:'<center>System Manager</center>'
			/*items:[
               {
                   //contentEl:'north-div',
                   title: 'Role Grid',
                   closable:true,
                   autoScroll:true
                   //autoLoad:ds
		       },{
                   //contentEl:'north-div',
                   title: 'User Role Configure',
                   closable:true,
                   autoScroll:true
                   //autoLoad:ds
		       }
		   ]*/
		   items:{
		   	title:'welcome',
		   	iconCls:'icon-tabs',
		   	autoLoad:{url:'index.jsp'}
		   }
		});
		
		// 当切换tab, 相应的tree node被选中,tab id 要与 tree node id 一致
		Application.tabs.on('tabchange',function(element,tab){
			var cls = tab.id;
	        if(cls){
	            /*var parts = cls.split('.');
	            var last = parts.length-1;
	            for(var i = 0; i < last; i++){ // things get nasty - static classes can have .
	                var p = parts[i];
	                var fc = p.charAt(0);
	                var staticCls = fc.toUpperCase() == fc;
	                if(p == 'Ext' || !staticCls){
	                    parts[i] = 'pkg-'+p;
	                }else if(staticCls){
	                    --last;
	                    parts.splice(i, 1);
	                }
	            }
	            parts[last] = cls;
	            Application.tree.selectPath('/root/'+parts.join('/'));*/
	            Application.tree.selectPath('/root/'+cls);
	        }
		});	    
	    
	    var viewport = new Ext.Viewport({
	    	title: 'Border Layout',
	    	layout:'border',
	    	items:[
	    		// 快速定义块,用BoxComponent
	    		/*new Ext.BoxComponent({
	    			region:'north',
	    			el:'north-div',
	    			split:true,
	    			height:100,
	    			minSize:100,
	    			maxSize:200,
	    			collapsible:true,
	    			title:'north'
	    		}),*/
	    		{
	    			region:'north',
	    			border:true,
	    			//contentEl:'north-div',
	    			split:true,
	    			height:100,
	    			minSize:100,
	    			maxSize:200,
	    			margins:'0 5 0 5',
	    			collapsible:true,
	    			title:'System Manager And Lookup Data',
	    			html:'<center>Manager</center>'	    			
	    		},
	    		Application.tabs,
	    		/*{
	    			region:'south',
	    			contentEl:'south-div',
	    			split:true,
	    			height:100,
	    			minSize:100,
	    			maxSize:200,
	    			collapsible:true,
	    			title:'south',
	    			margins:'0,0,0,0'	    			
	    		},*/
				Application.tree
	    		]
	    });	    		
    }
};

Ext.onReady(Application.layout.init);