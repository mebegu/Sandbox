// <copyright file="NatSetFactory.cs">Copyright ©  2015</copyright>

using System;
using Microsoft.Pex.Framework;
using NatSet;
using Microsoft.Pex.Framework.Explorable;
using System.Collections.Generic;

namespace NatSet
{
    /// <summary>A factory for NatSet.NatSet instances</summary>
    public static partial class NatSetFactory
    {
        /// <summary>A factory for NatSet.NatSet instances</summary>
        [PexFactoryMethod(typeof(global::NatSet.NatSet))]
        public static global::NatSet.NatSet Create(bool[] sm_bs, List<int> rest_list, int max_i)
        {
            global::NatSet.NatSet natSet
               = PexInvariant.CreateInstance<global::NatSet.NatSet>();
            PexInvariant.SetField<bool[]>((object)natSet, "sm", sm_bs);
            PexInvariant.SetField<List<int>>((object)natSet, "rest", rest_list);
            PexInvariant.SetField<int>((object)natSet, "max", max_i);
            PexInvariant.CheckInvariant((object)natSet);
            return natSet;

            // TODO: Edit factory method of NatSet
            // This method should be able to configure the object in all possible ways.
            // Add as many parameters as needed,
            // and assign their values to each field by using the API.
        }
    }
}
