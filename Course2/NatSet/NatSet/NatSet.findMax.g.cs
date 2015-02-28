// <copyright file="NatSet.findMax.g.cs">Copyright �  2015</copyright>
// <auto-generated>
// This file contains automatically generated unit tests.
// Do NOT modify this file manually.
// 
// When Pex is invoked again,
// it might remove or update any previously generated unit tests.
// 
// If the contents of this file becomes outdated, e.g. if it does not
// compile anymore, you may delete this file and invoke Pex again.
// </auto-generated>
using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Pex.Framework.Generated;

namespace NatSet
{
    public partial class NatSet
    {
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void findMaxThrowsContractException870()
{
    int i;
    i = this.findMax((bool[])null, (List<int>)null);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void findMaxThrowsContractException798()
{
    int i;
    bool[] bs = new bool[0];
    i = this.findMax(bs, (List<int>)null);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax200()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[0];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax245()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax617()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    bs[0] = true;
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(0, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax571()
{
    List<int> list;
    int i;
    int[] ints = new int[1];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(0, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax138()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[2];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void findMaxThrowsContractException557()
{
    int i;
    i = this.findMax((bool[])null, (List<int>)null);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void findMaxThrowsContractException33()
{
    int i;
    bool[] bs = new bool[0];
    i = this.findMax(bs, (List<int>)null);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax20001()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[0];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax24501()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax61701()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    bs[0] = true;
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(0, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax57101()
{
    List<int> list;
    int i;
    int[] ints = new int[1];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(0, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax13801()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[2];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void findMaxThrowsContractException848()
{
    int i;
    i = this.findMax((bool[])null, (List<int>)null);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void findMaxThrowsContractException52()
{
    int i;
    bool[] bs = new bool[0];
    i = this.findMax(bs, (List<int>)null);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax20002()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[0];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax24502()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax61702()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    bs[0] = true;
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(0, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax57102()
{
    List<int> list;
    int i;
    int[] ints = new int[1];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[1];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(0, i);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void findMax13802()
{
    List<int> list;
    int i;
    int[] ints = new int[0];
    list = new List<int>((IEnumerable<int>)ints);
    bool[] bs = new bool[2];
    i = this.findMax(bs, list);
    Assert.AreEqual<int>(-1, i);
}
    }
}
