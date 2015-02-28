// <copyright file="NatSet.remove.g.cs">Copyright �  2015</copyright>
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
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Pex.Framework.Generated;

namespace NatSet
{
    public partial class NatSet
    {
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void remove191()
{
    this.remove(0);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void removeThrowsContractException655()
{
    this.remove(int.MinValue);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[ExpectedException(typeof(ElementNotFoundException))]
public void removeThrowsElementNotFoundException241()
{
    this.remove(100);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void removeThrowsContractException177()
{
    this.remove(0);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void removeThrowsContractException590()
{
    this.remove(int.MinValue);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[ExpectedException(typeof(ElementNotFoundException))]
public void removeThrowsElementNotFoundException161()
{
    this.remove(100);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void removeThrowsContractException120()
{
    this.remove(0);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException]
public void removeThrowsContractException574()
{
    this.remove(int.MinValue);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[ExpectedException(typeof(ElementNotFoundException))]
public void removeThrowsElementNotFoundException86()
{
    this.remove(100);
}
    }
}
