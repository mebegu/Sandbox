// <copyright file="NatSet.insert.g.cs">Copyright �  2015</copyright>
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
using Microsoft.Pex.Engine.Exceptions;

namespace NatSet
{
    public partial class NatSet
    {
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void insert191()
{
    this.insert(0);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[PexRaisedContractException(PexExceptionState.Expected)]
public void insertThrowsContractException322()
{
    try
    {
      this.insert(int.MinValue);
      throw 
        new AssertFailedException("expected an exception of type ContractException");
    }
    catch(Exception ex)
    {
      if (!PexContract.IsContractException(ex))
        throw ex;
    }
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
public void insert265()
{
    this.insert(100);
}
[TestMethod]
[PexGeneratedBy(typeof(global::NatSet.NatSet))]
[Ignore]
[PexDescription("the test state was: duplicate path")]
public void insert919()
{
    this.insert(72);
}
    }
}
