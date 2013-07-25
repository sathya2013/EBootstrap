<%@include file="../include_inc.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="${ctx}/js/bootstrap-tree/css/bootstrap-tree.css" rel="stylesheet">
<script src="${ctx}/js/bootstrap-tree/js/bootstrap-tree.js"></script>	
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="${ctx}/management/index/center.do">我的工作台</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">模块管理</a>
					</li>
				</ul>
			</div>
				
			<div class="container-fluid">
            <div class="row-fluid">
                <div class="span3">
                    <div class="well">
                      <ul class="tree">
                        <li>
                          <a href="#" role="branch" class="tree-toggle" data-toggle="branch" data-value="Bootstrap_Tree">Bootstrap Tree</a>
                          <ul class="branch in">
                              <li><a href="#" role="leaf" id="nut">Docs</a></li>
                              <li><a href="#" role="leaf">Examples</a></li>
                              <li><a href="#" role="leaf">Credits</a></li>
                              <li><a href="http://www.cutterscrossing.com" role="leaf">Cutter's Crossing</a></li>
                              <li><a href="http://twitter.github.com/bootstrap" role="leaf">Bootstrap</a></li>
                          </ul>
                        </li>
                        <li><a href="htmlexample.html" role="branch" class="tree-toggle closed" data-toggle="branch" data-value="HTML_Example">HTML Ajax Example</a></li>
                        <li><a href="xmlexample.xml" role="branch" class="tree-toggle closed" data-toggle="branch" data-value="XML_Example">XML Ajax Example</a></li>
                        <li><a href="jsonexample.json" role="branch" class="tree-toggle closed" data-toggle="branch" data-value="JSON_Example">JSON Ajax Example</a></li>
                      </ul>
                    </div><!--/.well -->
                  </div><!--/span-->
                  
                  <div class="span9" id="mainContent">
                      <div class="row-fluid">
                          <div class="well span8" id="editorArea">
                              <h1>Bootstrap Tree</h1>
                              <p>This is a template is a basic example of Bootstrap Tree. <a href="http://twitter.github.com/bootstrap/index.html">Bootstrap</a> is an excellent front-end framework from the guys at Twitter. This simple component is an add on component for rendering a basic Tree with some HTML configuration.</p>
  							<p>Click a node, on the left, to see it in action.</p>
                              <!-- <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p> -->
                          </div>
                      </div>
                      <div class="span8" id="reporter"></div>
                  </div>
            </div>
        </div>

