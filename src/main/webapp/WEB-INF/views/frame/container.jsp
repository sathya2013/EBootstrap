<%@include file="../include_inc.jsp"%>
<div class="row-fluid">
	<!-- left menu starts -->
	<div class="span2 main-menu-span">
		<div class="well nav-collapse sidebar-nav">
		  <iframe name="frame_left" width="100%" height="100%" frameborder="0" scrolling="no" src="${ctx}/management/index/nav.do" onLoad ="javascript:SetWinHeight(this)"></iframe>
		</div>
	</div>
	
	<noscript>
		<div class="alert alert-block span10">
			<h4 class="alert-heading">Warning!</h4>
			<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
		</div>
	</noscript>
	
	 <div id="content" class="span10">
	    <iframe name="frame_center" width="100%" height="100%" frameborder="0" scrolling="no" src="${ctx}/management/index/center.do"  onLoad ="javascript:SetWinHeight(this)"></iframe>
	</div>
	
	<hr>
</div>

<div class="modal hide fade" id="myModal">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">×</button>
		<h3>Settings</h3>
	</div>
	<div class="modal-body">
		<p>Here settings can be configured...</p>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">Close</a>
		<a href="#" class="btn btn-primary">Save changes</a>
	</div>
</div>
<footer>
	<p class="pull-left">&copy; <a href="http://usman.it" target="_blank">Muhammad Usman</a> 2012</p>
	<p class="pull-right">Powered by: <a href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
</footer>
